package dev.custom.portals.blocks;

import java.util.Random;

import dev.custom.portals.CustomPortals;
import dev.custom.portals.util.EntityMixinAccess;
import dev.custom.portals.data.Portal;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.AreaHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.server.world.ServerWorld;

public class PortalBlock extends Block {
   public static final EnumProperty<Direction.Axis> AXIS;
   protected static final VoxelShape X_SHAPE;
   protected static final VoxelShape Z_SHAPE;
   protected static final VoxelShape Y_SHAPE;
    
   public PortalBlock(AbstractBlock.Settings settings) {
      super(settings);
      this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(AXIS, Direction.Axis.X));
   }

   @Override
   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      switch((Direction.Axis)state.get(AXIS)) {
      case Z:
         return Z_SHAPE;
      case Y:
         return Y_SHAPE;
      case X:
      default:
         return X_SHAPE;
      }
   }

   @Override
   public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      Portal portal = CustomPortals.PORTALS.get(world).getPortalFromPos(pos);
      if(portal == null)
         return;
      if(portal.hasLinked() && !portal.getLinked().getDimensionId().equals(portal.getDimensionId())) {
         if (portal.getLinked().getDimensionId().equals("minecraft:the_nether") && world.getDimension().isNatural() && world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && random.nextInt(2000) < world.getDifficulty().getId()) {
            while(world.getBlockState(pos).isOf(this)) {
               pos = pos.down();
            }
  
            if (world.getBlockState(pos).allowsSpawning(world, pos, EntityType.ZOMBIFIED_PIGLIN)) {
               Entity entity = EntityType.ZOMBIFIED_PIGLIN.spawn(world, (CompoundTag)null, (Text)null, (PlayerEntity)null, pos.up(), SpawnReason.STRUCTURE, false, false);
               if (entity != null) {
                  entity.method_30229();
               }
            }
         }
         if (portal.getLinked().getDimensionId().equals("minecraft:the_end") && world.getDimension().isNatural() && world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && random.nextInt(2000) < world.getDifficulty().getId()) {
            while(world.getBlockState(pos).isOf(this)) {
               pos = pos.down();
            }
  
            if (world.getBlockState(pos).allowsSpawning(world, pos, EntityType.ENDERMAN)) {
               Entity entity = EntityType.ENDERMAN.spawn(world, (CompoundTag)null, (Text)null, (PlayerEntity)null, pos.up(), SpawnReason.STRUCTURE, false, false);
               if (entity != null) {
                  entity.method_30229();
               }
            }
         }
      }
   }

   @Override
   public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
      super.onBreak(world, pos, state, player);
      Portal portal = CustomPortals.PORTALS.get(world).getPortalFromPos(pos);
      if(portal != null) {
         CustomPortals.PORTALS.get(world).unregisterPortal(portal);
         if(!world.isClient)
            CustomPortals.PORTALS.get(world).syncWithAll(((ServerWorld)world).getServer());
      }
   }
   
   @Override
   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
      Direction.Axis axis = direction.getAxis();
      Direction.Axis axis2 = (Direction.Axis)state.get(AXIS);
      boolean bl = axis2 == Direction.Axis.Y ? axis2 == axis && axis.isVertical() : axis2 != axis && axis.isHorizontal();
      if(!bl && !newState.isOf(this) && !(new AreaHelper(world, pos, axis2)).wasAlreadyValid()) {
         Portal portal = CustomPortals.PORTALS.get((World)world).getPortalFromPos(pos);
         if(portal != null) {
            CustomPortals.PORTALS.get((World)world).unregisterPortal(portal);
            if(!((World)world).isClient)
               CustomPortals.PORTALS.get((World)world).syncWithAll(((ServerWorld)world).getServer());
         }
         return Blocks.AIR.getDefaultState();
      }
      return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
   }
   
   @Override
   public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
      Portal portal = CustomPortals.PORTALS.get(world).getPortalFromPos(pos);
      if(portal != null && entity.canUsePortals()) {
         ((EntityMixinAccess)entity).setInCustomPortal(portal);
      }

      // For debugging purposes
      /*(if(portal != null) {
         if(portal.hasLinked())
            System.out.println("Linked Portal at " + portal.getLinked().getSpawnPos().getX() + ", " + portal.getLinked().getSpawnPos().getY() + ", " + portal.getLinked().getSpawnPos().getZ());
         else System.out.println("Portal is not linked!");
      } else System.out.println("No portal found!");*/
   }
   
   @Environment(EnvType.CLIENT)
   public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
      if (random.nextInt(100) == 0) {
         world.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
      }
  
      for(int i = 0; i < 4; ++i) {
         double d = (double)pos.getX() + random.nextDouble();
         double e = (double)pos.getY() + random.nextDouble();
         double f = (double)pos.getZ() + random.nextDouble();
         double g = ((double)random.nextFloat() - 0.5D) * 0.5D;
         double h = ((double)random.nextFloat() - 0.5D) * 0.5D;
         double j = ((double)random.nextFloat() - 0.5D) * 0.5D;
         int k = random.nextInt(2) * 2 - 1;
         if (!world.getBlockState(pos.west()).isOf(this) && !world.getBlockState(pos.east()).isOf(this)) {
            d = (double)pos.getX() + 0.5D + 0.25D * (double)k;
            g = (double)(random.nextFloat() * 2.0F * (float)k);
         } 
         else if (!world.getBlockState(pos.up()).isOf(this) && !world.getBlockState(pos.down()).isOf(this)) {
            e = (double)pos.getY() + 0.5D + 0.25D * (double)k;
            h = (double)(random.nextFloat() * 2.0F * (float)k);
         } else {
            f = (double)pos.getZ() + 0.5D + 0.25D * (double)k;
            j = (double)(random.nextFloat() * 2.0F * (float)k);
         }
         
         if(CustomPortals.PORTALS.get(world).getPortalFromPos(pos).hasLinked())
            world.addParticle(ParticleTypes.PORTAL, d, e, f, g, h, j);
      }
   
   }
  
   @Environment(EnvType.CLIENT)
   public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
      return ItemStack.EMPTY;
   }
   
   @Override
   public BlockState rotate(BlockState state, BlockRotation rotation) {
      switch(rotation) {
         // use COUNTERCLOCKWISE_90 to rotate around Y axis
         case COUNTERCLOCKWISE_90:
            switch((Direction.Axis)state.get(AXIS)) {
               case Z:
               case X:
                  return (BlockState)state.with(AXIS, Direction.Axis.Y);
               case Y:
                  return (BlockState)state.with(AXIS, Direction.Axis.X);
               default:
                  return state;
            }
         case CLOCKWISE_90:
            switch((Direction.Axis)state.get(AXIS)) {
               case Z:
                  return (BlockState)state.with(AXIS, Direction.Axis.X);
               case X:
               case Y:
                  return (BlockState)state.with(AXIS, Direction.Axis.Z);
               default:
                  return state;
            }
      default:
         return state;
      }
   }
  
   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(AXIS);
   }

   static {
      AXIS = Properties.AXIS;
      X_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
      Z_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
      Y_SHAPE = Block.createCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D);
   }
}
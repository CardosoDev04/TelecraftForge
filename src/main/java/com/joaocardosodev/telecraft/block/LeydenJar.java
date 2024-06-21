package com.joaocardosodev.telecraft.block;

import com.joaocardosodev.client.screen.ClientHooks;
import com.joaocardosodev.telecraft.blockentity.LeydenJarBlockEntity;
import com.joaocardosodev.telecraft.init.ModBlockEntities;
import com.joaocardosodev.telecraft.util.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public class LeydenJar extends HorizontalDirectionalBlock implements EntityBlock {


    private static final Logger LOGGER = Logger.getLogger(LeydenJar.class.getName());
    private static final VoxelShape SHAPE = Block.box(2,0,2,14,14,14);

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    public LeydenJar(Properties p) {
        super(p);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.LEYDEN_JAR_ENTITY.get().create(pos,state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(!level.isClientSide) {

            BlockEntity be = level.getBlockEntity(pos);

            if(be instanceof LeydenJarBlockEntity blockEntity) {


                DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openLeydenJarBlockScreen(pos));

                /*ItemStackHandler block_inventory = blockEntity.getInventory();

                ItemStack playerStack = player.getItemInHand(hand);

                if(playerStack.isEmpty()){
                    if(block_inventory.getStackInSlot(0).isEmpty()) {
                        player.sendSystemMessage(Component.literal("Theres no item in the jar"));
                        return InteractionResult.SUCCESS;
                    }

                    ItemStack extracted_item = block_inventory.extractItem(0,player.isCrouching() ? block_inventory.getSlotLimit(0) : 1 , false);

                    player.setItemInHand(hand,extracted_item);

                } else {
                    ItemStack to_insert = playerStack.copy();

                    to_insert.setCount(1);

                    ItemStack leftover = block_inventory.insertItem(0,to_insert,false);

                    ItemStack remainder = playerStack.copy();

                    remainder.setCount(remainder.getCount() - 1);
                    remainder.grow(leftover.getCount());
                    player.setItemInHand(hand, remainder);

                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.sidedSuccess(level.isClientSide());*/
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return TickableBlockEntity.getTickerHelper(level);
    }

    @Override
    public void onRemove(BlockState oldState, Level level, BlockPos pos, BlockState state, boolean isMoving) {
       if(!level.isClientSide) {
           BlockEntity be = level.getBlockEntity(pos);

           if(be instanceof LeydenJarBlockEntity blockEntity) {
               ItemStackHandler inventory = blockEntity.getInventory();
               for(int i = 0; i < inventory.getSlots(); i++) {
                   ItemStack stack = inventory.getStackInSlot(i);
                   var entity = new ItemEntity(level,pos.getX() + 0.5,pos.getY() + 0.5,pos.getZ()+ 0.5,stack);
                   level.addFreshEntity(entity);
               }
           }
       }
        super.onRemove(state, level, pos, oldState, isMoving);

    }
}

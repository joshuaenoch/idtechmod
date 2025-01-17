package com.idtech.block;
import com.idtech.BaseMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class RubberBlock extends Block
{
    private static Properties properties = Properties.of(Material.STONE);

    public static Block INSTANCE = new RubberBlock(properties).setRegistryName(BaseMod.MODID, "rubber");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    public RubberBlock(Properties properties){
        super(properties);
    }

    @Override
    public void stepOn(Level levelIn, BlockPos posIn, BlockState blockStateIn, Entity entityIn) {
        super.stepOn(levelIn, posIn, blockStateIn, entityIn);

        entityIn.setDeltaMovement(0,5,0);
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos posIn, BlockState blockStateIn, BlockEntity entity, ItemStack stack) {
        super.playerDestroy(world, player, posIn, blockStateIn, entity, stack);
        for(int i = 0; i < 5; i++){
            int randX = (int)(Math.random()*10)-5;
            int randZ = (int)(Math.random()*10)-5;
            PrimedTnt tnt = new PrimedTnt(world, posIn.getX()+randX, posIn.getY(), posIn.getZ()+randZ, null);
            world.addFreshEntity(tnt);
        }
    }
}

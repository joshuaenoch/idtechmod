package com.idtech.block;


import com.idtech.BaseMod;
import com.idtech.world.FunnyTreeGrower;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
//import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber
public class BlockMod {

    //Basic Block
    public static final Block CASTLE_WALL = BlockUtils.createBasicBlock("castlewall", Material.STONE);
    public static final Item CASTLE_WALL_ITEM = BlockUtils.createBlockItem(CASTLE_WALL, CreativeModeTab.TAB_MISC);
    public static final Block GEL_ORE_BLOCK = BlockUtils.createBasicBlock("geloreblock", Material.STONE, 0.5f);
    public static final Item GEL_ORE_BLOCK_ITEM = BlockUtils.createBlockItem(GEL_ORE_BLOCK, CreativeModeTab.TAB_MISC);
    public static final Block FUNNY_LOG = BlockUtils.createBasicBlock("funnylog", Material.WOOD);
    public static final Item FUNNY_LOG_ITEM = BlockUtils.createBlockItem(FUNNY_LOG, CreativeModeTab.TAB_MISC);


    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BaseMod.MODID);
    public static final RegistryObject<Block> FUNNY_SAPLING = BLOCKS.register("funny_sapling",
            () -> new SaplingBlock(new FunnyTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {

        event.getRegistry().register(CASTLE_WALL_ITEM);
        event.getRegistry().register(GEL_ORE_BLOCK_ITEM);
        event.getRegistry().register(RubberBlock.ITEM);
        event.getRegistry().register(CreepingMoldBlock.ITEM);
        event.getRegistry().register(PigBlock.ITEM);
        //event.getRegistry().register(FUNNY_LOG_ITEM);
        event.getRegistry().register(LuckyBlock.ITEM);
        //event.getRegistry().register(LuckyBlock2.ITEM);
        //event.getRegistry().register(SpecialTorch.ITEM);
        event.getRegistry().register(SuspiciousDirt.ITEM);
        //event.getRegistry().register(TestWater.ITEM);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        event.getRegistry().register(CASTLE_WALL);
        event.getRegistry().register(GEL_ORE_BLOCK);
        event.getRegistry().register(RubberBlock.INSTANCE);
        event.getRegistry().register(CreepingMoldBlock.INSTANCE);
        event.getRegistry().register(PigBlock.INSTANCE);
        event.getRegistry().register(SpecialTorch.INSTANCE);
        //event.getRegistry().register(FUNNY_LOG);
        event.getRegistry().register(LuckyBlock.INSTANCE);
        //event.getRegistry().register(LuckyBlock2.INSTANCE);
        event.getRegistry().register(SuspiciousDirt.INSTANCE);
        //event.getRegistry().register(TestWater.INSTANCE);
    }
}







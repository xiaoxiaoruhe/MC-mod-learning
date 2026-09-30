package com.tsfmn.zzx.item;

import com.tsfmn.zzx.TutorialMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {

    // 1. 物品注册：使用统一的 register 方法
    public static final Item FIRST_ITEM = register("first_item", new Item(new Item.Settings()));
    public static final Item ICE_ETHER = register("ice_ether", new Item(new Item.Settings()));

    // 注意：路径中包含 '/' 是符合规范的，常用于分类
    public static final Item RAW_ICE_ETHER = register("material/raw_ice_ether", new Item(new Item.Settings()));

    // 2. 核心注册方法（重载设计，提供不同粒度的调用方式）

    // 最底层：通过 RegistryKey 注册，并处理 BlockItem 的特殊逻辑
    public static Item register(RegistryKey<Item> key, Item item) {
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registries.ITEM, key, item);
    }

    // 中间层：通过 Identifier 注册
    public static Item register(Identifier id, Item item) {
        return register(RegistryKey.of(Registries.ITEM.getKey(), id), item);
    }

    // 最顶层：通过 String 注册（自动拼接 Mod ID）
    public static Item register(String id, Item item) {
        return register(new Identifier(TutorialMod.MOD_ID, id), item);
    }

    public static void init(){
        TutorialMod.LOGGER.info("Initializing Mod Items");
    }

    public static void addItemToItemGroup(FabricItemGroupEntries entries){
        //把物品加载到fabric的物品栏里面
        entries.add(ModItems.ICE_ETHER);
        entries.add(ModItems.RAW_ICE_ETHER);
    }

    public static void addItemToItemGroup2(FabricItemGroupEntries entries){
        entries.add(ModItems.FIRST_ITEM);
    }


    public static void registerItems(){
        //注册物品
        //这段代码注册了一个监听器，告诉 Fabric：‘当你准备构建自然物品栏时，请调用我的方法，到时候我自己会往里面加东西
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemToItemGroup);
        //这段代码注册了一个监听器，告诉 Fabric：‘当你准备构建食材物品栏时，请调用我的方法，到时候我自己会往里面加东西
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToItemGroup2);
    }
}
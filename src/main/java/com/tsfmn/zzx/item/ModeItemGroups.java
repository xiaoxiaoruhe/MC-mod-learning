package com.tsfmn.zzx.item;

import com.tsfmn.zzx.TutorialMod;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * 自定义物品栏分组类 - 定义模组的自定义创造模式物品栏
 */
public class ModeItemGroups {

    // 定义教程模组物品栏的注册键
    public static final RegistryKey<ItemGroup> TUTORIAL_GROUP = register("tutorial_group");

    /**
     * 注册物品栏的私有辅助方法
     * @param id 物品栏ID
     * @return 注册键
     */
    private static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(TutorialMod.MOD_ID,id));
    }

    /**
     * 直接注册的第二个教程物品栏
     * 这个物品栏会在类加载时自动注册
     */
    public static final ItemGroup TUTORIAL_GROUP2 = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(TutorialMod.MOD_ID, "tutorial_group2"),
            ItemGroup.create(null, - 1) // 使用默认排序值
                    .displayName(Text.translatable("itemGroup.tutorial_group2")) // 设置显示名称
                    .icon(() -> new ItemStack(ModItems.FIRST_ITEM)) // 设置物品栏图标为第一个自定义物品
                    .entries((displayContext, entries) -> { // 配置物品栏内容
                        entries.add(new ItemStack(ModItems.FIRST_ITEM)); // 添加第一个自定义物品
                        entries.add(new ItemStack(Items.DIAMOND)); // 添加钻石作为示例
                        entries.add(new ItemStack(Blocks.DIAMOND_BLOCK)); // 添加钻石块作为示例
                    }).build()
    );

    /**
     * 手动注册第一个教程物品栏的方法
     * 这个方法需要在模组初始化时显式调用
     */
    public static void registerGroup() {
        // 注册自定义物品栏到游戏中
        Registry.register(
                Registries.ITEM_GROUP,
                TUTORIAL_GROUP, // 使用预先定义的注册键
                ItemGroup.create(null,-1) // 创建在顶部行，序号为7的物品栏
                        .displayName(Text.translatable("itemGroup.tutorial_group")) // 设置显示名称
                        .icon(() -> new ItemStack(ModItems.ICE_ETHER)) // 设置物品栏图标为冰乙太
                        .entries((displayContext, entries) -> { // 配置物品栏内容
                            entries.add(new ItemStack(ModItems.ICE_ETHER)); // 添加冰乙太
                            entries.add(new ItemStack(ModItems.RAW_ICE_ETHER)); // 添加原材料冰乙太
                        }).build()
        );
    }


}

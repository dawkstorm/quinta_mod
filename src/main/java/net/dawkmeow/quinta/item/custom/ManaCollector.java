package net.dawkmeow.quinta.item.custom;

import net.dawkmeow.quinta.util.IEntityDataSaver;
import net.dawkmeow.quinta.util.ManaData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManaCollector extends Item {
    public ManaCollector(Settings settings) {
        super(settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.7F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    private final List<Block> MANA_BLOCKS = Arrays.asList(
            Blocks.SHORT_GRASS,
            Blocks.SEAGRASS,
            Blocks.TALL_SEAGRASS,
            Blocks.TALL_GRASS,
            Blocks.MOSS_BLOCK,
            Blocks.GLOW_LICHEN,
            Blocks.OAK_SAPLING,
            Blocks.SPRUCE_SAPLING,
            Blocks.JUNGLE_SAPLING,
            Blocks.BIRCH_SAPLING,
            Blocks.ACACIA_SAPLING,
            Blocks.DARK_OAK_SAPLING,
            Blocks.CHERRY_SAPLING,
            Blocks.AZALEA,
            Blocks.FLOWERING_AZALEA,
            Blocks.DANDELION,
            Blocks.POPPY,
            Blocks.BLUE_ORCHID,
            Blocks.ALLIUM,
            Blocks.AZURE_BLUET,
            Blocks.RED_TULIP,
            Blocks.ORANGE_TULIP,
            Blocks.WHITE_TULIP,
            Blocks.PINK_TULIP,
            Blocks.OXEYE_DAISY,
            Blocks.CORNFLOWER,
            Blocks.LILY_OF_THE_VALLEY,
            Blocks.TORCHFLOWER,
            Blocks.PINK_PETALS,
            Blocks.CRIMSON_ROOTS,
            Blocks.WARPED_ROOTS,
            Blocks.WEEPING_VINES,
            Blocks.TWISTING_VINES,
            Blocks.VINE,
            Blocks.LILAC,
            Blocks.ROSE_BUSH,
            Blocks.PITCHER_PLANT,
            Blocks.LARGE_FERN
    );

    private final List<Block> FLOWER_BLOCKS = Arrays.asList(
            Blocks.DANDELION,
            Blocks.POPPY,
            Blocks.BLUE_ORCHID,
            Blocks.ALLIUM,
            Blocks.AZURE_BLUET,
            Blocks.RED_TULIP,
            Blocks.ORANGE_TULIP,
            Blocks.WHITE_TULIP,
            Blocks.PINK_TULIP,
            Blocks.OXEYE_DAISY,
            Blocks.CORNFLOWER,
            Blocks.LILY_OF_THE_VALLEY,
            Blocks.TORCHFLOWER,
            Blocks.PINK_PETALS,
            Blocks.SUNFLOWER,
            Blocks.LILAC,
            Blocks.ROSE_BUSH,
            Blocks.PITCHER_PLANT
    );

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if(attacker instanceof PlayerEntity player){
            if(!player.isCreative()){
                stack.damage(1, player, EquipmentSlot.MAINHAND);
            }
            if(target.isDead()) {
                if (target instanceof PassiveEntity entity || target instanceof SpiderEntity || target instanceof PatrolEntity) {
                    ManaData.addMana((IEntityDataSaver) attacker, 1.f);
                } else if (target instanceof HostileEntity entity) {
                    ManaData.removeMana((IEntityDataSaver) attacker, 1.f);
                }
                player.sendMessage(Text.literal("Mana: " + ((IEntityDataSaver) player).getPersistentData()
                        .getFloat("mana")).fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var blockPos = context.getBlockPos();
        Block block = context.getWorld().getBlockState(blockPos).getBlock();

        if(MANA_BLOCKS.contains(block)){
            var blockUnderneathPos = new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
            var blockUnderneath = context.getWorld().getBlockState(blockUnderneathPos).getBlock();
            if(blockUnderneath == Blocks.GRASS_BLOCK) {
                context.getWorld().setBlockState(blockUnderneathPos, Blocks.DIRT.getDefaultState());
            }
            var stack = context.getPlayer().getStackInHand(Hand.MAIN_HAND);
            if(!context.getPlayer().isCreative()){
                stack.damage(1, context.getPlayer(), EquipmentSlot.MAINHAND);
            }
            if(FLOWER_BLOCKS.contains(block)){
                context.getWorld().playSound(null, blockPos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS);
                context.getWorld().setBlockState(blockPos, Blocks.WITHER_ROSE.getDefaultState());
            }
            else{
                context.getWorld().breakBlock(blockPos, false);
            }
            if(block == Blocks.SHORT_GRASS || block == Blocks.TALL_GRASS ||
                     block == Blocks.VINE){
                ManaData.addMana((IEntityDataSaver) context.getPlayer(), 0.25f);
            }
            else if (block == Blocks.SEAGRASS || block == Blocks.TALL_SEAGRASS
                    || block == Blocks.MOSS_BLOCK || block == Blocks.GLOW_LICHEN
                    || block == Blocks.OAK_SAPLING || block == Blocks.SPRUCE_SAPLING
                    || block == Blocks.JUNGLE_SAPLING || block == Blocks.ACACIA_SAPLING
                    || block == Blocks.BIRCH_SAPLING || block == Blocks.DARK_OAK_SAPLING
                    || block == Blocks.CHERRY_SAPLING || block == Blocks.AZALEA
                    || block == Blocks.FLOWERING_AZALEA || block == Blocks.DANDELION || block == Blocks.POPPY
                    || block == Blocks.RED_TULIP || block == Blocks.ORANGE_TULIP
                    || block == Blocks.PINK_TULIP || block == Blocks.WHITE_TULIP
                    || block == Blocks.AZURE_BLUET || block == Blocks.OXEYE_DAISY
                    || block == Blocks.CORNFLOWER || block == Blocks.MANGROVE_PROPAGULE
                    ) {
                ManaData.addMana((IEntityDataSaver) context.getPlayer(), 0.5f);
            }
            else if (block == Blocks.LILY_OF_THE_VALLEY || block == Blocks.ALLIUM
                    || block == Blocks.BLUE_ORCHID || block == Blocks.PINK_PETALS
                    || block == Blocks.SUNFLOWER || block == Blocks.LILAC
                    || block == Blocks.ROSE_BUSH || block == Blocks.PEONY
                    || block == Blocks.PITCHER_PLANT){
                ManaData.addMana((IEntityDataSaver) context.getPlayer(), 1.0f);
            }
            else if (block == Blocks.CRIMSON_ROOTS || block == Blocks.WARPED_ROOTS
                    || block == Blocks.WEEPING_VINES || block == Blocks.TWISTING_VINES) {
                ManaData.removeMana((IEntityDataSaver) context.getPlayer(), 1.0f);
            }
            else if(block == Blocks.TORCHFLOWER){
                ManaData.addMana((IEntityDataSaver) context.getPlayer(), 2.0f);
            }

            context.getPlayer().sendMessage(Text.literal("Mana: " + ((IEntityDataSaver) context.getPlayer()).getPersistentData()
                    .getFloat("mana")).fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
            return ActionResult.SUCCESS;
        }
        else {
            context.getPlayer().sendMessage(Text.translatable("quinta.message.must_be_used_on_grass")
                    .fillStyle(Style.EMPTY.withColor(Formatting.DARK_RED)), true);
            return ActionResult.FAIL;
        }
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }
}

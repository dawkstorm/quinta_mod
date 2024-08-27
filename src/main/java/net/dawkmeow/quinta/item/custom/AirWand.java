package net.dawkmeow.quinta.item.custom;

import net.dawkmeow.quinta.Quinta;
import net.dawkmeow.quinta.util.IEntityDataSaver;
import net.dawkmeow.quinta.util.ManaData;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

enum AirWandModes {
    LIFT_ENTITIES,
    SCOOTER,
    KNOCKBACK_ENTITIES,
    BUBBLE_UNDER_WATER,
    SHIELD
}
public class AirWand extends Item {
    public int level = 0;
    public AirWandModes mode;
    public LivingEntity capturedEntity;
    public boolean isEnabled;

    public AirWand(Settings settings) {
        super(settings);
        level = 0;
        mode = AirWandModes.LIFT_ENTITIES;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(capturedEntity != null) {
            user.setCurrentHand(hand);
            isEnabled = true;
            return TypedActionResult.consume(user.getStackInHand(hand));
        }
        return TypedActionResult.fail(user.getStackInHand(hand));
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 100000;
    }

    public double distance;

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
        if(!world.isClient() && !capturedEntity.isDead() && isEnabled &&
                capturedEntity != user && ((IEntityDataSaver) user).getPersistentData().getFloat("mana") > 0) {
            switch (mode) {
                case LIFT_ENTITIES:
                    ManaData.removeMana((IEntityDataSaver) user, 0.05f);
                    Vec3d newPos = new Vec3d(user.getRotationVector().getX() * distance,
                            user.getRotationVector().getY() * distance,
                            user.getRotationVector().getZ() * distance);
                    newPos = new Vec3d(newPos.getX() + user.getPos().getX(),
                            newPos.getY() + user.getPos().getY() + 0.5d,
                            newPos.getZ() + user.getPos().getZ());

                    capturedEntity.requestTeleport(newPos.getX(), newPos.getY(), newPos.getZ());
                    break;
            }
            if(user instanceof PlayerEntity player) {
                player.sendMessage(Text.literal("Mana: " + ((IEntityDataSaver) player).getPersistentData()
                        .getFloat("mana")).fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
                if(((IEntityDataSaver) player).getPersistentData().getFloat("mana") <= 0){
                    onStoppedUsing(stack, world, user, remainingUseTicks);
                }
            }

        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
        isEnabled = false;
        capturedEntity.setNoGravity(false);;
        capturedEntity = user;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        capturedEntity = entity;
        distance = user.distanceTo(capturedEntity);
        capturedEntity.setNoGravity(true);
        return super.useOnEntity(stack, user, entity, hand);
    }

    public static final Identifier BASE_KNOCKBACK_MODIFIER_ID = Identifier.ofVanilla("base_attack_knockback");

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 5.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                        new EntityAttributeModifier(BASE_KNOCKBACK_MODIFIER_ID, 1.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public int getMaxCount() {
        return 1;
    }

}

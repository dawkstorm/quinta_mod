package net.dawkmeow.quinta.item.custom;

import net.dawkmeow.quinta.Quinta;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

enum AirWandModes {
    LIFT_ENTITIES,
    KNOCKBACK_ENTITIES,
    SCOOTER,
    BUBBLE_UNDER_WATER,
    SHIELD
}
public class AirWand extends Item {
    public int level = 0;
    public AirWandModes mode;
    public AirWand(Settings settings) {
        super(settings);
        level = 0;
        mode = AirWandModes.LIFT_ENTITIES;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        double distance = user.distanceTo(entity);
        switch (mode){
            case LIFT_ENTITIES:
                Vec3d newPos = new Vec3d(user.getRotationVector().getX() * distance,
                        user.getRotationVector().getY() * distance,
                        user.getRotationVector().getZ() * distance);
                newPos = new Vec3d(newPos.getX() + user.getPos().getX(),
                        newPos.getY() + user.getPos().getY() + 0.5d,
                        newPos.getZ() + user.getPos().getZ());

                entity.teleport(newPos.getX(), newPos.getY(), newPos.getZ(), false);
                break;
        }
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
                        new EntityAttributeModifier(BASE_KNOCKBACK_MODIFIER_ID, 2.0, EntityAttributeModifier.Operation.ADD_VALUE),
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

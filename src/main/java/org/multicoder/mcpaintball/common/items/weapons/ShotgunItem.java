package org.multicoder.mcpaintball.common.items.weapons;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.multicoder.mcpaintball.common.MCPaintballSounds;
import org.multicoder.mcpaintball.common.entity.paintball.PaintballEntity;
import org.multicoder.mcpaintball.common.utility.PaintballTeam;

public class ShotgunItem extends Item
{
    public ShotgunItem()
    {
        super(new Properties().setNoRepair().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        if(!level.isClientSide()){
            CompoundTag PersistData = player.getPersistentData();
            if(PersistData.contains("mcpaintball.teamsTag")){
                CompoundTag TeamData = PersistData.getCompound("mcpaintball.teamsTag");
                if(TeamData.contains("team")){
                    PaintballTeam Team = PaintballTeam.values()[TeamData.getInt("team")];
                    AbstractArrow Paintball_1 = new PaintballEntity(Team.getPaintball(),player,level);
                    AbstractArrow Paintball_2 = new PaintballEntity(Team.getPaintball(),player,level);
                    AbstractArrow Paintball_3 = new PaintballEntity(Team.getPaintball(),player,level);
                    Paintball_1.shootFromRotation(player,player.getXRot(),player.getYRot() - 16f,0f,2f,0f);
                    Paintball_2.shootFromRotation(player,player.getXRot(),player.getYRot(),0f,2f,0f);
                    Paintball_3.shootFromRotation(player,player.getXRot(),player.getYRot() + 16f,0f,2f,0f);
                    level.addFreshEntity(Paintball_1);
                    level.addFreshEntity(Paintball_2);
                    level.addFreshEntity(Paintball_3);
                    level.playSound(null,player.blockPosition(), MCPaintballSounds.SHOT.get(), SoundSource.PLAYERS,1f,1f);
                }
            }
        }
        return super.use(level, player, hand);
    }
}

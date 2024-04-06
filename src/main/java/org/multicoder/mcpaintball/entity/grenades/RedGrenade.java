package org.multicoder.mcpaintball.entity.grenades;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.multicoder.mcpaintball.capability.PlayerTeamCapabilityProvider;
import org.multicoder.mcpaintball.init.entityinit;
import org.multicoder.mcpaintball.init.iteminit;
import org.multicoder.mcpaintball.network.Networking;
import org.multicoder.mcpaintball.network.packets.TeamPointS2CPacket;
import org.multicoder.mcpaintball.util.level.LevelUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class RedGrenade extends ThrowableItemProjectile
{

    public RedGrenade( double pX, double pY, double pZ, Level pLevel)
    {
        super((EntityType<? extends ThrowableItemProjectile>) entityinit.RED_GRENADE.get(), pX, pY, pZ, pLevel);
    }

    public RedGrenade(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel)
    {
        super(pEntityType, pLevel);
    }

    public RedGrenade(LivingEntity pShooter, Level pLevel)
    {
        super((EntityType<? extends ThrowableItemProjectile>) entityinit.RED_GRENADE.get(), pShooter, pLevel);
    }

    @Override
    protected void onHit(HitResult pResult)
    {
        if(!level.isClientSide())
        {
            if(getOwner() instanceof ServerPlayer)
            {
                ServerPlayer Thrower = (ServerPlayer) getOwner();
                BlockPos pos = new BlockPos(pResult.getLocation());
                Explosion ex = this.level.explode(this,pos.getX(),pos.getY(),pos.getZ(),3, Explosion.BlockInteraction.DESTROY);
                AtomicInteger Points = new AtomicInteger(0);
                LevelUtils.GetPointsFromExplosion(Points,ex,Thrower);
                Thrower.getCapability(PlayerTeamCapabilityProvider.CAPABILITY).ifPresent(cap ->
                {
                    cap.AddPoints(Points.get());
                    Networking.sendToPlayer(new TeamPointS2CPacket(cap.GetPoints()),Thrower);
                });
            }

        }
    }

    @Override
    protected Item getDefaultItem()
    {
        return iteminit.RED_GRENADE.get();
    }
}

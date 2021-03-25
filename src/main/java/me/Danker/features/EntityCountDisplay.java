package me.Danker.features;

import me.Danker.DankersSkyblockMod;
import me.Danker.commands.MoveCommand;
import me.Danker.commands.ScaleCommand;
import me.Danker.commands.ToggleCommand;
import me.Danker.events.RenderOverlay;
import me.Danker.handlers.TextRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

/**
 * @author CuzImClicks
 */

public class EntityCountDisplay {

    int entityAmount = 0;
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (DankersSkyblockMod.tickAmount % 10 != 0) return;
        if (!ToggleCommand.showEntityAmount) return;
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.theWorld;
        if (world == null) {
            return;
        }
        List<Entity> entities = world.getLoadedEntityList();

        entityAmount = entities.toArray().length;

    }

    @SubscribeEvent
    public void renderPlayerInfo(RenderOverlay event) {
        if (!ToggleCommand.showEntityAmount) return;
        String text = "Entities: " + entityAmount;
        new TextRenderer(Minecraft.getMinecraft(), text, 20, 80, ScaleCommand.lividHpScale);
    }
}

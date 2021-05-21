package me.Danker.features.inGameGui;



import me.Danker.events.RenderOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.Danker.features.inGameGui.LootTrackerGuiBase.steveUsed;
import static me.Danker.features.inGameGui.LootTrackerGuiBase.skinMap;



public class renderGuiScreen extends GuiScreen {
    public static EntityOtherPlayerMP entityOtherPlayerMP = null;
    public static String SkyblockID = null;




    public void render(String SkyblockID)
    {
        this.SkyblockID  = SkyblockID;
        String raw = LootTrackerGuiBase.resources.get(SkyblockID);
        if (raw.startsWith("skull:"))
        {
            String name = raw.substring("skull:".length());



        }
        else if (raw.startsWith("skin:"))
        {
            entityOtherPlayerMP = new CreatePlayer(SkyblockID).entityPlayer();
            GlStateManager.color(1, 1, 1, 1);

            new LootTrackerGuiBase().drawEntity(entityOtherPlayerMP, 115, 153, 15, -25);


        }

        else if (raw.startsWith("item:")) {
            String name = raw.substring("item:".length());

        }
        else if (raw.startsWith("book:")){
            String name = raw.substring("book:".length());

        }

    }

    @SubscribeEvent
    public void RenderGUIEvent (RenderOverlay event) {
            if (entityOtherPlayerMP != null) {
                    if(steveUsed)
                    {
                        entityOtherPlayerMP = null;
                        entityOtherPlayerMP = new CreatePlayer(SkyblockID).entityPlayer();
                        GlStateManager.color(1, 1, 1, 1);


                    }
                new LootTrackerGuiBase().drawEntity(entityOtherPlayerMP, 15, 53, 15,25);

            }
        }
    }


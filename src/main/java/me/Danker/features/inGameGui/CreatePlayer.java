package me.Danker.features.inGameGui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static me.Danker.features.inGameGui.LootTrackerGuiBase.*;


public class CreatePlayer {

    String name;
    private EntityOtherPlayerMP entityPlayer = null;

    private ResourceLocation playerLocationSkin = null;
    private ResourceLocation playerLocationCape = null;
    private String skinType = "default";





    public CreatePlayer(String name) {
        this.name = name;
    }



    public EntityOtherPlayerMP entityPlayer () {

        String id = LootTrackerGuiBase.resources.get(name);

                LootTrackerGuiBase.downloadPlayerSkin(name);

                GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);

                entityPlayer = new EntityOtherPlayerMP(getDummyWorld(), gameProfile) {
                    public ResourceLocation getLocationSkin() {
                        if (!skinMap.containsKey(name)) {
                            steveUsed = true;
                            return DefaultPlayerSkin.getDefaultSkinLegacy();
                        }
                        else {
                            steveUsed = false;
                            return skinMap.get(name);
                        }
                    }

                    public ResourceLocation getLocationCape() {
                        return playerLocationCape;
                    }

                    public String getSkinType() {
                        return "default";
                    }
                };
                entityPlayer.setAlwaysRenderNameTag(false);
                entityPlayer.setCustomNameTag("");





        try {
            Minecraft.getMinecraft().getSkinManager().loadProfileTextures(entityPlayer.getGameProfile(), new SkinManager.SkinAvailableCallback() {


                @Override
                public void skinAvailable(MinecraftProfileTexture.Type type, ResourceLocation location, MinecraftProfileTexture profileTexture)

                {
                    switch (type) {
                        case SKIN:
                            playerLocationSkin = location;
                            if(skinType == null) {
                                skinType = "default";
                            }
                            break;
                        case CAPE:
                            playerLocationCape = location;
                    }
                }

            }, false);

        } catch(Exception e){ Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("An exeption occured"));       }


        return entityPlayer;
    }







}

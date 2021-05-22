package me.Danker.features.inGameGui;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import static me.Danker.features.inGameGui.LootTrackerGuiBase.*;


public class CreatePlayer {

    String name;
    private EntityOtherPlayerMP entityPlayer = null;

    private ResourceLocation playerLocationSkin = null;
    private ResourceLocation playerLocationCape = null;
    private static final HashMap<String,BufferedImage> buffer = new HashMap<>();








    public CreatePlayer(String name) {
        this.name = name;
    }



    public EntityOtherPlayerMP entityPlayer () {

        String id = LootTrackerGuiBase.resources.get(name);
        String url = "https://textures.minecraft.net/texture/" + id.substring("skin:".length());



                GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);


                    entityPlayer = new EntityOtherPlayerMP(getDummyWorld(), gameProfile) {
                        @Override
                        public ResourceLocation getLocationSkin() {

                            playerLocationSkin = downloadSkin(url);

                            return playerLocationSkin == null ? DefaultPlayerSkin.getDefaultSkinLegacy() : playerLocationSkin;
                        }
                        @Override
                        public ResourceLocation getLocationCape() {
                            return playerLocationCape;
                        }
                        @Override
                        public String getSkinType() {
                            return "default";
                        }

                        @Override
                        public boolean hasSkin() {
                            return true;
                        }


                    };
                    entityPlayer.setAlwaysRenderNameTag(false);
                    entityPlayer.setCustomNameTag("");
        return entityPlayer;
    }

    public EntityOtherPlayerMP getEntityPlayer() {
        return entityPlayer;
    }

    public ResourceLocation downloadSkin (String url){


        if (!skinMap.containsKey(name)) {
            ResourceLocation output = null;
            DynamicTexture texture = null;

            new Thread(() -> {

                BufferedImage image;



                try {
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(DefaultPlayerSkin.getDefaultSkinLegacy().getResourceDomain()));
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(DefaultPlayerSkin.getDefaultSkinLegacy().getResourcePath()));

                    URL url1 = new URL(url);
                    image = ImageIO.read (url1);
                   // AffineTransform affineTransform = AffineTransform.getScaleInstance(1,-1);
                   // affineTransform.translate(0,-image.getHeight(null)); //(Math.PI/4),image.getWidth()/2,image.getHeight()/2);
                   // AffineTransformOp op = new AffineTransformOp(affineTransform,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                   // image = op.filter(image,null);


                    buffer.put(name, image);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(e.getLocalizedMessage()));
                } catch (IOException e) {
                    e.printStackTrace();
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(e.getLocalizedMessage()));
                }
            }).start();



                if (buffer.containsKey(name)) {
                    texture = new DynamicTexture(buffer.get(name));
                }

                output = Minecraft.getMinecraft().renderEngine.getDynamicTextureLocation(name, texture);



            if (output != null) {
                skinMap.put(name, output);


            }
        }
        return skinMap.get(name);
    }
}

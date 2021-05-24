package me.Danker.features.inGameGui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;


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
                    
                   if (image.getHeight() == 32)
                   {

                       BufferedImage bufferedImage = new BufferedImage(64,64,image.getType());
                       Graphics2D graphics2D = bufferedImage.createGraphics();
                       graphics2D.setColor(new Color(255,255,255,255));// no color IntelliJ is trolling
                       graphics2D.fillRect(0,32,64,32);
                       graphics2D.drawImage(image,0,0,null);

                       graphics2D.drawImage(image.getSubimage(0,16,16,16),16,48,null);//leg
                       graphics2D.drawImage(image.getSubimage(40,16,16,16),32,48,null);//arm
                       graphics2D.dispose();
                       buffer.put(name, bufferedImage);
                   }
                   else {
                       buffer.put(name, image);
                   }




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

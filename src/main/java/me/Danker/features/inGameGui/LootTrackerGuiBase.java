package me.Danker.features.inGameGui;


import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.client.multiplayer.WorldClient;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class LootTrackerGuiBase {


    private static final WorldClient DUMMY_WORLD = new WorldClient(null, new WorldSettings(0L, WorldSettings.GameType.SURVIVAL,
            false, false, WorldType.DEFAULT), 0, null, null);


    private static EntityArmorStand DummyArmorStand;
    private static EntityZombie revenant;
    private static EntitySpider spooder1;
    private static EntityCaveSpider spooder2;
    private static EntityWolf sven;
    private static EntityPlayer yeti;
    private static EntityPlayer frozenSteve;
    private static EntityPlayer grinch;
    private static EntityPlayer scarecrow;
    private static EntityPlayer werewolf;
    private static EntityPlayer phantom_Fisher;
    private static EntityPlayer gim_Reaper;
    private static EntityPlayer nurse_Shark;
    private static EntityPlayer blue_Shark;
    private static EntityPlayer tiger_Shark;
    private static EntityPlayer great_White_Shark;
    private static EntityZombie seaWalker;
    private static EntityZombie hydra;
    private static EntityRabbit carrot_King;
    private static EntitySquid sea_Squid;
    private static EntityOcelot catfish;
    private static EntityWitch sea_Witch;
    private static EntitySilverfish sea_Leech;
    private static EntityHorse sea_Zombie_Horse;
    private static EntityIronGolem deep_Sea_Protector;
    private static EntitySnowman sea_Snowman;
    private static EntityChicken sea_Chicken_Jockey1;
    private static EntityZombie sea_Chicken_Jockey2;
    private static EntitySkeleton sea_Skeleton;
    // Sea creatures
    public static final HashMap<String, String> resources = new HashMap<>();


    static {
        resources.put("DIGESTED_MOSQUITO", "item:ROTTEN_FLESH");
        resources.put("REVENANT_FLESH", "item:ROTTEN_FLESH");// Digest Mosquito, Revenant Flesh
        resources.put("CRITICAL;", "book:BOOK");
        resources.put("SMITE;", "book:BOOK");
        resources.put("BANE_OF_ARTHROPODS;", "book:BOOK");
        resources.put("ULTIMATE_CHIMERA;", "book:BOOK");// wolf, spooder, zombie, chimera
        resources.put("WOLF_TOOTH", "item:GHAST_TEAR"); // wolf teeth, shark theeth, sorrow
        resources.put("NURSE_SHARK_TOOTH", "item:GHAST_TEAR");
        resources.put("BLUE_SHARK_TOOTH", "item:GHAST_TEAR");
        resources.put("TIGER_SHARK_TOOTH", "item:GHAST_TEAR");
        resources.put("GREAT_WHITE_SHARK_TOOTH", "item:GHAST_TEAR");
        resources.put("SORROW", "item:GHAST_TEAR");
        resources.put("ADAPTIVE_BOOTS", "item:GREY_LEATHER_BOOTS");
        resources.put("THORNS_BOOTS", "item:GREY_LEATHER_BOOTS");//Adaptive: Color:        , Spirit Boots Color:
        resources.put("SPIRIT_SWORD", "item:IRON_SWORD"); //Spirit sword, livid dagger; giants sword, Necromancer Sword, Withercloak Sword
        resources.put("LIVID_DAGGER", "item:IRON_SWORD");
        resources.put("GIANTS_SWORD", "item:IRON_SWORD");
        resources.put("NECROMANCER_SWORD", "item:IRON_SWORD");
        resources.put("WITHER_CLOAK", "item:IRON_SWORD");
        resources.put("ITEM_SPIRIT_BOW", "item:BOW"); //Spirit Bow, Last Breath
        resources.put("LAST_BREATH", "item:BOW");

        resources.put("HAMSTER_WHEEL", "item:OAK_TRAPDOOR"); //hamster wheels
        resources.put("RED_CLAW_EGG", "item:MUSHROOM_COW_SPAWN_EGG"); // Red claw egg
        resources.put("GRIZZLY_BAIT", "item:RAW_SALMON"); //Grizzly Bait
        resources.put("OVERFLUX_CAPACITOR", "item:NETHER_QUARTZ"); //Overflux

        resources.put("TARANTULA_WEB", "item:STRING"); // Tarantula Web
        resources.put("TOXIC_ARROW_POISON", "item:GREEN_DYE"); // Toxic arrow poison
        resources.put("FLY_SWATTER", "item:GOLDEN_SHOVEL"); //Fly Swatter

        resources.put("FOUL_FLESH", "item:CHARCOAL"); //Foul Flesh
        resources.put("SCYTHE_BLADE", "item:DIAMOND"); //Scythe Blade
        resources.put("REVENANT_VISCERA", "item:COOKED_PORKCHOP"); // Revenant Viscera

        resources.put("GRIFFIN_FEATHER", "item:FEATHER"); // Griffin Feather
        // resources.put("ANCIENT_CLAW","item:FLINT"); // Ancient Claw
        resources.put("CROWN_OF_GREED", "item:GOLDEN_HELMET"); //Crown of Greed
        resources.put("ANTIQUE_REMEDIES", "item:AZURE_BLUET"); //Antique Remedies
        resources.put("DAEDALUS_STICK", "item:STICK"); // Daedalus Stick

        resources.put("FUMING_POTATO_BOOK", "item:written_book"); //Fuming Potato

        resources.put("BONZO_STAFF", "item:BLAZE_ROD"); //bonzo staff

        resources.put("STONE_BLADE", "item:STONE_SWORD"); //Adaptive blade

        resources.put("ADAPTIVE_CHESTPLATE", "item:GREY_LEATHER_CHESTPLATE"); //Adaptive
        resources.put("ADAPTIVE_LEGGINGS", "item:GREY_LEATHER_LEGGINGS");

        resources.put("SHADOW_ASSASSIN_CHESTPLATE", "item:BLACK_LEATHER_CHESTPLATE"); //SA-Armor
        resources.put("SHADOW_ASSASSIN_LEGGINGS", "item:BLACK_LEATHER_LEGGINGS");
        resources.put("SHADOW_ASSASSIN_BOOTS", "item:BLACK_LEATHER_BOOTS");
        resources.put("SHADOW_FURY", "item:DIAMOND_SWORD"); //shadow furry

        resources.put("GOLEM_POPPY", "item:ROSE"); //Ancient Rose
        resources.put("NECROMANCER_LORD_CHESTPLATE", "item:PURPLE_LEATHER_CHESTPLATE"); // Necromancer Lord
        resources.put("NECROMANCER_LORD_LEGGINGS", "item:PURPLE_LEATHER_LEGGINGS");
        resources.put("NECROMANCER_LORD_BOOTS", "item:PURPLE_LEATHER_BOOTS");

        resources.put("SHADOW_WARP_SCROLL", "item:BOOK_AND_QUILL"); //Scrolls
        resources.put("IMPLOSION_SCROLL", "item:BOOK_AND_QUILL"); //Scrolls
        resources.put("WITHER_SHIELD_SCROLL", "item:BOOK_AND_QUILL"); //Scrolls
        resources.put("WITHER_CHESTPLATE", "item:BLACK_WITHER_CHESTPLATE"); //WITHER ARMOR
        resources.put("WITHER_LEGGINGS", "item:BLACK_WITHER_LEGGINGS");
        resources.put("WITHER_BOOTS", "item:BLACK_WITHER_BOOTS");

        resources.put("GHOST_BOOTS", "item:DARK_GREY_LEATHER_BOOTS");


        //SlayerRunes
        resources.put("PESTILENCE_RUNE", "rune:a8c4811395fbf7f620f05cc3175cef1515aaf775ba04a01045027f0693a90147");
        resources.put("SNAKE_RUNE", "rune:2c4a65c689b2d36409100a60c2ab8d3d0a67ce94eea3c1f7ac974fd893568b5d");
        resources.put("BITE_RUNE", "rune:43a1ad4fcc42fb63c681328e42d63c83ca193b333af2a426728a25a8cc600692");
        resources.put("SPIRIT_RUNE", "rune:c738b8af8d7ce1a26dc6d40180b3589403e11ef36a66d7c4590037732829542e");
        resources.put("COUTURE_RUNE", "skull:734fb3203233efbae82628bd4fca7348cd071e5b7b52407f1d1d2794e31799ff");
        resources.put("SPIDER_CATALYST", "skull:983b30e9d135b05190eea2c3ac61e2ab55a2d81e1a58dbb26983a14082664");
        resources.put("TARANTULA_TALISMAN", "skull:442cf8ce487b78fa203d56cf01491434b4c33e5d236802c6d69146a51435b03d");
        resources.put("UNDEAD_CATALYST", "skull:80625369b0a7b052632db6b926a87670219539922836ac5940be26d34bf14e10");
        resources.put("BEHEADED_HORROR", "skull:dbad99ed3c820b7978190ad08a934a68dfa90d9986825da1c97f6f21f49ad626");
        resources.put("REVENANT_CATALYST", "skull:b88cfafa5f03f8aef042a143799e964342df76b7c1eb461f618e398f84a99a63");
        resources.put("SHARD_OF_THE_SHREDDED", "skull:70c5cc728c869ecf3c6e0979e8aa09c10147ed770417e4ba541aac382f0");
        resources.put("WARDEN_HEART", "skull:d45f4d139c9e89262ec06b27aaad73fa488ab49290d2ccd685a2554725373c9b");
        resources.put("TARANTULA;", "skull:8300986ed0a04ea79904f6ae53f49ed3a0ff5b1df62bba622ecbd3777f156df8");
        resources.put("BABY_YETI;", "skull:ab126814fc3fa846dad934c349628a7a1de5b415021a03ef4211d62514d5");
        resources.put("MEGALODON;", "skull:a94ae433b301c7fb7c68cba625b0bd36b0b14190f20e34a7c8ee0d9de06d53b9");
        resources.put("null", "skull:538071721cc5b4cd406ce431a13f86083a8973e1064d2f8897869930ee6e5237");
        resources.put("WASHED_UP_SOUVENIR", "skull:3777f04644dec5f80bfeaa7401acfbbc150eb25d3ff8be4220e7c34426cd727c");
        resources.put("CROCHET_TIGER_PLUSHIE", "skull:3bddf5bae3af592858df9a150109e88c1caed8ba51e793c25aa03ca1b25db");
        resources.put("DWARF_TURTLE_SHELMET", "skull:664698fea5ec3f2fd7db7a8e3f4e989a1716035c2bd3666434ba1af58157");
        resources.put("MINOS_RELIC", "skull:40b4648cbd817c7b5fc654c9c054e360d81bbfe1a00f214657a174e3e0d07d21");

        resources.put("RECOMBOBULATOR_3000", "skull:57ccd36dc8f72adcb1f8c8e61ee82cd96ead140cf2a16a1366be9b5a8e3cc3fc");

        resources.put("SCARF_STUDIES", "skull:6de4ab129e137f9f4cbf7060318ee1748dc39da9b5d129a8da0e614e2337693");

        resources.put("ADAPTIVE_HELMET", "skull:16f0d34aaa714377983701c1bbe9b3f6e7b30cb38571bc0a2fd7541d7edb1637");

        resources.put("SPIRIT_WING", "skull:e29ad76c2459911c2bbacdcd0a7b82e0835567e3e5134b05a6f5af669dd88b83");
        resources.put("SPIRIT_BONE", "skull:b8a79c512cff1a9f47319fd77efd06f673a772aa97597436b0160c409c8a2e60");
        resources.put("SPIRIT;", "skull:8d9ccc670677d0cebaad4058d6aaf9acfab09abea5d86379a059902f2fe22655");//Epic and Legendary

        resources.put("SHADOW_ASSASSIN_HELMET", "skull:9b8af52ef2f72c3bf5ece6e70a82f1371959ce3fcb736c500305caddc505c5e2");
        resources.put("AOTE_STONE", "skull:5cb7c21cc43dc17678ee6f16591ffaab1f637c37f4f6bbd8cea497451d76db6d");

        resources.put("PRECURSOR_EYE", "skull:72c0683b2019ca3d3947273e394bfca1b4d71b61b67b39474c2d6d73a9b67508");
        resources.put("NECROMANCER_LORD_HELMET", "skull:852f92694ad81e6358b43072aaef25f8e0099184bb4c7fd388e4adf70acc7e0f");

        resources.put("WITHER_BLOOD", "skull:bf5d20e0600174dcbab7745d944832fb2063c2bd1490c6505901b28bfaacd8e5");
        resources.put("AUTO_RECOMBOBULATOR", "skull:5dff8dbbab15bfbb11e23b1f50b34ef548ad9832c0bd7f5a13791adad0057e1b");
        resources.put("WITHER_HELMET", "skull:691129409789589ae1b43e1d828c06fc9a213b78348a68b7313bdd401d7485cc");

        resources.put("VOLTA", "skull:63a405fb286dbb32e9b3908f60948f0207306c825e63ac9e626ed1dbb2f7a2be");
        resources.put("PLASMA", "skull:75aa8332bbec4a958fea2be64b8f1682f5d8247451aa2b7569edd0498437d706");
        resources.put("BAG_OF_CASH", "skull:e36e94f6c34a35465fce4a90f2e25976389eb9709a12273574ff70fd4daa6852");

        resources.put("RED_GIFT", "skull:b73a2114136b8ee4926caa51785414036a2b76e4f1668cb89d99716c421");

        //resources.put("REVENANT_ZOMBIE", "skin:1fc0184473fe882d2895ce7cbc8197bd40ff70bf10d3745de97b6c2a9c5fc78f");

        resources.put("YETI", "skin:3e356010b338951cf8bb82ec79bfe29c19cb3763e41f7bf8feaa04a0cc0995cf");//3e356010b338951cf8bb82ec79bfe29c19cb3763e41f7bf8feaa04a0cc0995cf
        resources.put("FROZEN_STEVE", "skin:9785dbcd93914ecdb68502e7f17ef1d7");//ec7bc0390b1dbbda32526cbef34ea84e2c2b8176229d585fe415c22df6462568
        resources.put("GRINCH", "skin:54c55f2aec39490294f22db9d2e381cb"); //holding red gift

        resources.put("SCARECROW", "skin:eb52929d29704ff1a803b3bff9817c47"); //wearing pumpkin c3832ce7fbf00dd0eaa537918612cd2f2e9a2aa8b6ec5821932d38768f955ce9
        resources.put("WEREWOLF", "skin:");
        resources.put("PHANTOM_FISHER", "skin:");//holding phantom rod
        resources.put("GRIM_REAPER", "skin:"); //holding unentchanted iron hoe

        resources.put("SEAWALKER", "skin:"); //Zombie not a player
        resources.put("HYDRA", "head:21ffbda92442873d54051e4e17ea47278cb3b747d798070ba83bc4a9de58dd"); //Zombie not a player head:21ffbda92442873d54051e4e17ea47278cb3b747d798070ba83bc4a9de58dd, Dark blue leather armor, only boots entchanted

        resources.put("NURSE_SHARK", "skin:");
        resources.put("BLUE_SHARK", "skin:");
        resources.put("TIGER_SHARK", "skin:");
        resources.put("GREAT_WHITE_SHARK", "skin:");
    }


    private static HashMap<String, ItemStack> skullMap = new HashMap<>();
    public static HashMap<String, ResourceLocation> skinMap = new HashMap<>();

    public static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);

    public static boolean steveUsed = false;


    //Spooder,Wolf,    Squid[night],witch , ocelot , Rabbit Type99, silverfish, iron golem, snowman, zombie horse

    // skeleton--> blue amour without helmet and entchanted bow; Chicken Jockey --> entchanted chainmail armor, entchanted diamond sword; Sea emperor
    //missing: red gift
//idea: minion skin for grinch

    public static WorldClient getDummyWorld() {
        return DUMMY_WORLD;
    }


    public static ItemStack createItemStack(Item item, int meta, String name, boolean enchanted) {
        ItemStack itemStack = new ItemStack(item, 1, meta);

        if (name != null) {
            itemStack.setStackDisplayName(name);
        }

        if (enchanted) {
            itemStack.addEnchantment(Enchantment.unbreaking, 0);
        }

        return itemStack;
    }


    public static ItemStack createSkull(String SbID, String name, String link) {

        if (skullMap.containsKey(link)) return skullMap.get(link);

        String display = "{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/" + link + "\"}}}";
        String displayB64 = Base64.getEncoder().encodeToString(display.getBytes());

        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagCompound extraAttributes = new NBTTagCompound();
        NBTTagCompound owner = new NBTTagCompound();
        NBTTagCompound properties = new NBTTagCompound();
        NBTTagList textures = new NBTTagList();
        NBTTagCompound textures_0 = new NBTTagCompound();

        ItemStack itemStack = new ItemStack(Items.skull, 1, 3);

        textures_0.setString("Value", displayB64);
        textures.appendTag(textures_0);

        properties.setTag("textures", textures);
        owner.setTag("Properties", properties);
        nbt.setTag("SkullOwner", owner);
        itemStack.setTagCompound(nbt);

        if (name != null) {
            itemStack.setStackDisplayName(name);
        }
        if (SbID != null) {
            extraAttributes.setString("id", SbID);
            itemStack.setTagInfo("ExtraAttributes", extraAttributes);
        }

        skullMap.put(link, itemStack);

        return itemStack;
    }


    public static ItemStack createEntchantedBook(String name, String entchantment, int entchantmentLvl) {
        NBTTagCompound enchantments = new NBTTagCompound();

        ItemStack itemStack = new ItemStack(Items.enchanted_book, 1);
        if (name != null) {
            itemStack.setStackDisplayName(name);
        }

        if (entchantment != null && entchantmentLvl >= 0) {
            enchantments.setString(entchantment, String.valueOf(entchantmentLvl));
            NBTTagCompound extraAttributes = itemStack.getTagCompound().getCompoundTag("ExtraAttributes");
            extraAttributes.setTag("enchantments", enchantments);
        }
        return itemStack;
    }


    public static Entity createEntity() {
        Entity entity = new EntityZombie(getDummyWorld());
        return entity;
    }


    public void drawEntity(EntityOtherPlayerMP entity, float x, float y, float yaw, int scale) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, 50.0F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float renderYawOffset = entity.renderYawOffset;
        float f1 = entity.rotationYaw;
        float f2 = entity.rotationPitch;
        float f3 = entity.prevRotationYawHead;
        float f4 = entity.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(25, 1.0F, 0.0F, 0.0F);
        entity.renderYawOffset = yaw;
        entity.rotationYaw = 0F;
        entity.rotationPitch = 0F;
        entity.rotationYawHead = yaw;
        entity.prevRotationYawHead = yaw;
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);


        entity.renderYawOffset = renderYawOffset;
        entity.rotationYaw = f1;
        entity.rotationPitch = f2;
        entity.prevRotationYawHead = f3;
        entity.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    }














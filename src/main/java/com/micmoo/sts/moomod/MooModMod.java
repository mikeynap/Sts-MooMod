package com.micmoo.sts.moomod;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.micmoo.sts.moomod.cards.blue.PowerShift;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.micmoo.sts.moomod.cards.blue.Firewall;
import com.micmoo.sts.moomod.cards.blue.LoadBalancer;
import com.micmoo.sts.moomod.cards.blue.MultiRecursion;
import com.micmoo.sts.moomod.cards.blue.OrbDrain;
import com.micmoo.sts.moomod.cards.blue.ZenForm;
import com.micmoo.sts.moomod.cards.blue.Sandbox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

@SpireInitializer
public class MooModMod implements
        PostInitializeSubscriber,
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber,
        EditStringsSubscriber,
        PostDeathSubscriber,
        StartGameSubscriber,
        MaxHPChangeSubscriber
{
    public static final boolean IS_DEBUG = false;
    public static final Logger logger = LogManager.getLogger(MooModMod.class.getSimpleName());

    private static SpireConfig modConfig = null;

    // Beta card asset paths
    public static final String BETA_ATTACK = MooModMod.assetPath("images/cards/replayBetaAttack.png");
    public static final String BETA_SKILL  = MooModMod.assetPath("images/cards/replayBetaSkill.png");
    public static final  String BETA_POWER  = MooModMod.assetPath("images/cards/replayBetaPower.png");



    public static void initialize()
    {
        BaseMod.subscribe(new MooModMod());

        try {
            Properties defaults = new Properties();
            defaults.put("startingMooMod", Boolean.toString(false));
            modConfig = new SpireConfig("MooMod", "Config", defaults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String assetPath(String path)
    {
        return path;
    }

    public static boolean startingMooMod()
    {
        if (modConfig == null) {
            return true;
        }

        return modConfig.getBool("startingMooMod");
    }

    public static void loadData()
    {
        logger.info("Loading Save Data");
        try {
            SpireConfig config = new SpireConfig("MooMod", "SaveData");

            /* MNTODO: load logic here
            BottledRain.load(config);
            DisguiseKit.load(config);
            MysteriousPyramids.load(config);
            Zylophone.load(config);
            EmptyBottle.load(config);
            DuctTape.load(config);

            */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveData()
    {
        logger.info("Saving Data");
        try {
            SpireConfig config = new SpireConfig("MooMod", "SaveData");
                      /* MNTODO: load logic here

            BottledRain.save(config);
            DisguiseKit.save(config);
            MysteriousPyramids.save(config);
            Zylophone.save(config);
            EmptyBottle.save(config);
            */
            // Duct Tape saving is handled separately
            config.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearData()
    {
        logger.info("Clearing Saved Data");
        try {
            SpireConfig config = new SpireConfig("MooMod", "SaveData");
            config.clear();
            config.save();
            /* MNTODO: clear logic here

            BottledRain.clear();
            DisguiseKit.clear();
            MysteriousPyramids.clear();
            Zylophone.clear();
            EmptyBottle.clear();
*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveStartGame()
    {
        loadData();
        System.out.printf("mn: FloorNum %d %b %d", AbstractDungeon.floorNum, IS_DEBUG,AbstractDungeon.player.masterDeck.size() );
        if (IS_DEBUG && AbstractDungeon.floorNum == 0 && AbstractDungeon.player.masterDeck.size() == 10){ 
             AbstractDungeon.player.masterDeck.addToBottom(new Sandbox());
             AbstractCard c = new Sandbox();
             c.upgrade();
             AbstractDungeon.player.masterDeck.addToBottom(c);
        }
        /*
        if (AbstractDungeon.floorNum <= 1 && CardCrawlGame.dungeon instanceof Exordium) {
            AbstractCard savedCard = TinFlute.getSavedItem();
            if (savedCard != null) {
                AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(savedCard, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                TinFlute.deleteSave();
            }
        } */
    }

    @Override
    public void receivePostInitialize()
    {
        //ModPanel settingsPanel = new ModPanel();
        /* MNTODO: Load Image */
        //BaseMod.registerModBadge(ImageMaster.loadImage(assetPath("images/MooMod/modBadge.png")), "MooMod", "kiooeht", "TODO", settingsPanel);
        /* MNTODO: Load Events */
        /*
        BaseMod.addEvent(TheFatedDie.ID, TheFatedDie.class);
        BaseMod.addEvent(Experiment.ID, Experiment.class, TheCity.ID);
        // Only appears if player has Bottle relic. See TheBottlerPatch
        BaseMod.addEvent(TheBottler.ID, TheBottler.class, TheBeyond.ID);
        BaseMod.addEvent(UpdateBodyText.ID, UpdateBodyText.class);

        BaseMod.addMonster(GrandSnecko.ID, GrandSnecko::new);
        BaseMod.addMonster(MusketHawk.ID, MusketHawk::new);

        BaseMod.addBoss(TheBeyond.ID, GrandSnecko.ID, assetPath("images/ui/map/boss/grandSnecko.png"), assetPath("images/ui/map/bossOutline/grandSnecko.png"));
        BaseMod.addBoss(TheCity.ID, MusketHawk.ID, assetPath("images/ui/map/boss/musketHawk.png"), assetPath("images/ui/map/bossOutline/musketHawk.png"));
    */
    }

    @Override
    public void receivePostDeath()
    {
        /*
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(TinFlute.ID)) {
            TinFlute flute = (TinFlute) AbstractDungeon.player.getRelic(TinFlute.ID);
            flute.onDeath();
        } */
    }

    @Override
    public void receiveEditCards()
    {
        System.out.println("receiveEditCards...");

        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveEditRelics()
    {
        /* MNTODO: AddRelics */
        /*
        BaseMod.addRelic(new Icosahedron(), RelicType.SHARED);
        BaseMod.addRelic(new BlackHole(), RelicType.SHARED);
        BaseMod.addRelic(new VirtuousBlindfold(), RelicType.SHARED);
        BaseMod.addRelic(new PeanutButter(), RelicType.SHARED);
        BaseMod.addRelic(new CuriousFeather(), RelicType.SHARED);
        BaseMod.addRelic(new Metronome(), RelicType.SHARED);
        BaseMod.addRelic(new FunFungus(), RelicType.SHARED);
        BaseMod.addRelic(new CrackedHourglass(), RelicType.SHARED);
        BaseMod.addRelic(new ScarierMask(), RelicType.SHARED);
        BaseMod.addRelic(new DeadTorch(), RelicType.SHARED);
        BaseMod.addRelic(new BottledHeart(), RelicType.SHARED);
        BaseMod.addRelic(new DisguiseKit(), RelicType.SHARED);
        BaseMod.addRelic(new Teleporter(), RelicType.SHARED);
        BaseMod.addRelic(new MysteriousPyramids(), RelicType.SHARED);
        BaseMod.addRelic(new AstralHammer(), RelicType.SHARED);
        BaseMod.addRelic(new PrototypeTalaria(), RelicType.SHARED);
        BaseMod.addRelic(new Spice(), RelicType.SHARED);
        BaseMod.addRelic(new TinFlute(), RelicType.SHARED);
        BaseMod.addRelic(new GlazedTorus(), RelicType.SHARED);
        BaseMod.addRelic(new Backtick(), RelicType.SHARED);
        BaseMod.addRelic(new Test447(), RelicType.SHARED);
        BaseMod.addRelic(new BundleOfHerbs(), RelicType.SHARED);
        BaseMod.addRelic(new SphereOfDissonance(), RelicType.SHARED);
        BaseMod.addRelic(new Stopwatch(), RelicType.SHARED);
        BaseMod.addRelic(new HerbalPaste(), RelicType.SHARED);
        BaseMod.addRelic(new MedicalManual(), RelicType.SHARED);
        BaseMod.addRelic(new HollowSoul(), RelicType.SHARED);
        BaseMod.addRelic(new CrystalStatue(), RelicType.SHARED);
        BaseMod.addRelic(new BottledRain(), RelicType.SHARED);
        BaseMod.addRelic(new Zylophone(), RelicType.SHARED);
        BaseMod.addRelic(new EmptyBottle(), RelicType.SHARED);
        BaseMod.addRelic(new DuctTape(), RelicType.SHARED);
        //BaseMod.addRelic(new BloodyCrown(), RelicType.SHARED);
        BaseMod.addRelic(new OldNail(), RelicType.SHARED);
        BaseMod.addRelic(new NiceRug(), RelicType.SHARED);

        BaseMod.addRelic(new RGBLights(), RelicType.BLUE);
        BaseMod.addRelic(new BallOfYels(), RelicType.BLUE);

        if (hasConstructMod) {
            BaseMod.addRelicToCustomPool(new ClockworkCow(), constructmod.patches.AbstractCardEnum.CONSTRUCTMOD);
        }
        if (hasFruityMod) {
            BaseMod.addRelicToCustomPool(new DustyCowl(), fruitymod.seeker.patches.AbstractCardEnum.SEEKER_PURPLE);
        }
        if (hasInfiniteSpire) {
            BaseMod.addRelic(new MobiusCoin(), RelicType.SHARED);
        }
    */
    }

    @Override
    public void receiveEditKeywords()
    {
    }

    @Override
    public void receiveEditStrings()
    {
        /* MNTODO: LocalizationString
        BaseMod.loadCustomStringsFile(RelicStrings.class, assetPath("localization/MooMod-RelicStrings.json"));
        BaseMod.loadCustomStringsFile(CardStrings.class, assetPath("localization/MooMod-CardStrings.json"));
        BaseMod.loadCustomStringsFile(CardStrings.class, assetPath("localization/MooMod-IcosahedronStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class, assetPath("localization/MooMod-OrbStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, assetPath("localization/MooMod-PowerStrings.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class, assetPath("localization/MooMod-EventStrings.json"));
 */
    }
    void AddAndUnlockCard(AbstractCard c)
    {
	BaseMod.addCard(c);
	UnlockTracker.unlockCard(c.cardID);
    }

    private void autoAddCards() throws URISyntaxException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        AddAndUnlockCard(new PowerShift());
        AddAndUnlockCard(new OrbDrain());
        AddAndUnlockCard(new ZenForm());
        AddAndUnlockCard(new MultiRecursion());
        AddAndUnlockCard(new LoadBalancer());
        AddAndUnlockCard(new Firewall());
        
        if (IS_DEBUG){
            AddAndUnlockCard(new Sandbox());
        }
    }
    

    @Override
    public int receiveMapHPChange(int amount)
    {
        /*if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(BottledHeart.ID)) {
            BottledHeart relic = (BottledHeart) AbstractDungeon.player.getRelic(BottledHeart.ID);
            return relic.onMaxHPChange(amount);
        }
        return amount; */
        return 0;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.relics;

import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.micmoo.sts.moomod.MooModMod;
import static com.micmoo.sts.moomod.MooModMod.BETA_RELIC;
import com.micmoo.sts.moomod.cards.blue.Sandbox;
import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.clapper.util.classutil.AbstractClassFilter;
import org.clapper.util.classutil.AndClassFilter;
import org.clapper.util.classutil.ClassFilter;
import org.clapper.util.classutil.ClassFinder;
import org.clapper.util.classutil.ClassInfo;
import org.clapper.util.classutil.ClassModifiersClassFilter;
import org.clapper.util.classutil.InterfaceOnlyClassFilter;
import org.clapper.util.classutil.NotClassFilter;

public class SandboxRelic extends MooRelic {

    public final static String ID = "moomod:SandboxRelic";

    public SandboxRelic() {
        super(ID, BETA_RELIC, RelicTier.COMMON, LandingSound.FLAT);
        /*img = ImageMaster.loadImage("images/relics/" + tmp);
        largeImg = ImageMaster.loadImage("images/largeRelics/" + tmp);
        outlineImg = ImageMaster.loadImage("images/relics/outline/" + tmp);
        if (img == null || outlineImg == null) {
            img = ImageMaster.loadImage(MooModMod.assetPath("images/relics/" + tmp));
            largeImg = ImageMaster.loadImage(MooModMod.assetPath("images/largeRelics/" + tmp));
            outlineImg = ImageMaster.loadImage(MooModMod.assetPath("images/relics/outline/" + tmp));
        }*/

    }

    @Override
    public String getUpdatedDescription() {
        return "Desc";
    }

    @Override
    public void obtain() {
        try {
            ClassFinder finder = new ClassFinder();
            URL url = MooModMod.class.getProtectionDomain().getCodeSource().getLocation();
            finder.add(new File(url.toURI()));

            ClassFilter filter
                    = new AndClassFilter(
                            new NotClassFilter(new InterfaceOnlyClassFilter()),
                            new NotClassFilter(new AbstractClassFilter()),
                            new ClassModifiersClassFilter(Modifier.PUBLIC)
                    );
            Collection<ClassInfo> foundClasses = new ArrayList<>();
            finder.findClasses(foundClasses, filter);

            for (ClassInfo classInfo : foundClasses) {
                Class<?> cls = MooModMod.class.getClassLoader().loadClass(classInfo.getClassName());
                boolean isRelic = false;
                Class<?> superCls = cls;
                while (superCls != null) {
                    superCls = superCls.getSuperclass();
                    if (superCls == null) {
                        break;
                    }
                    if (superCls.equals(MooRelic.class)) {
                        isRelic = true;
                        break;
                    }
                }
                MooRelic relic = (MooRelic) cls.newInstance();

                if (!isRelic || relic == null || relic instanceof SandboxRelic) {
                    continue;
                }
                RelicType type = relic.getType();
                PlayerClass cl = AbstractDungeon.player.chosenClass;
                if (type == RelicType.SHARED
                        || (type == RelicType.BLUE && cl == PlayerClass.DEFECT)
                        || (type == RelicType.GREEN && cl == PlayerClass.THE_SILENT)
                        || (type == RelicType.RED && cl == PlayerClass.IRONCLAD)) {

                    System.out.println(classInfo.getClassName());
                    AbstractDungeon.player.getRelic(relic.relicId);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | URISyntaxException ex) {
            Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public AbstractRelic makeCopy() {
        return new SandboxRelic();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.cards.blue;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import com.micmoo.sts.moomod.MooModMod;
import static com.micmoo.sts.moomod.MooModMod.BETA_SKILL;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
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

public class Sandbox
extends CustomCard {
    public static final String ID = "sandbox";
    public static final String NAME = "Sandbox";
    public static final String DESCRIPTION = "Gain all moomod cards.";
    public static final String IMG_PATH = BETA_SKILL;
    private static final int COST = 0;

    public Sandbox() {
        
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

       this.baseMagicNumber = this.magicNumber = 0;
       // this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

      //  this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

       // this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        p.hand.clear();
        try {
                    ClassFinder finder = new ClassFinder();
        URL url = MooModMod.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC)
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            Class<?> cls = MooModMod.class.getClassLoader().loadClass(classInfo.getClassName());
            boolean isCard = false;
            Class<?> superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.equals(AbstractCard.class)) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }
            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) cls.newInstance();

            
            if (this.upgraded){
                card.upgrade();
            }
                p.hand.addToHand(card);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException  | URISyntaxException ex) {
            Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sandbox();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
        }
    }
    
    /**
 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
 *
 * @param packageName The base package
 * @return The classes
 * @throws ClassNotFoundException
 * @throws IOException
 */
private static Class[] getClasses(String packageName)
        throws ClassNotFoundException, IOException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    assert classLoader != null;
    String path = packageName.replace('.', '/');
    Enumeration<URL> resources = classLoader.getResources(path);
    List<File> dirs = new ArrayList<File>();
    while (resources.hasMoreElements()) {
        URL resource = resources.nextElement();
        dirs.add(new File(resource.getFile()));
    }
    ArrayList<Class> classes = new ArrayList<Class>();
    for (File directory : dirs) {
        classes.addAll(findClasses(directory, packageName));
    }
    return classes.toArray(new Class[classes.size()]);
}

/**
 * Recursive method used to find all classes in a given directory and subdirs.
 *
 * @param directory   The base directory
 * @param packageName The package name for classes found inside the base directory
 * @return The classes
 * @throws ClassNotFoundException
 */
private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
    List<Class> classes = new ArrayList<Class>();
    if (!directory.exists()) {
        return classes;
    }
    File[] files = directory.listFiles();
    for (File file : files) {
        if (file.isDirectory()) {
            assert !file.getName().contains(".");
            classes.addAll(findClasses(file, packageName + "." + file.getName()));
        } else if (file.getName().endsWith(".class")) {
            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
        }
    }
    return classes;
}
}


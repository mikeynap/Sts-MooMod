/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.relics;

import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import static com.micmoo.sts.moomod.MooModMod.BETA_RELIC;

/**
 *
 * @author mnapolit
 */
public class GoldenOrbs extends MooRelic {

    public static final String ID = "moomod:GoldenOrbs";
    public static final int AMOUNT = 1;
    public static final RelicType TYPE = RelicType.BLUE;

    public GoldenOrbs() {
        super(ID, BETA_RELIC, AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return "Gain 1 gold everytime you evoke an orb.";
    }

    @Override
    public void onEvokeOrb(AbstractOrb ammo) {
        AbstractDungeon.player.gainGold(AMOUNT);
        this.flash();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GoldenOrbs();
    }

}

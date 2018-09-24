/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.relics;

import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import static com.micmoo.sts.moomod.MooModMod.BETA_RELIC;

/**
 *
 * @author mnapolit
 */
public class Darkness extends MooRelic {

    public static final String ID = "moomod:Darkness";
    public static final RelicType TYPE = RelicType.BLUE;

    public Darkness() {
        super(ID, BETA_RELIC, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return "At the beginning of your turn, trigger any channeled darkness orbs one time.";
    }

    @Override
    public void atTurnStart() {
        final AbstractPlayer p = AbstractDungeon.player;
        for (int i = 0; i < p.orbs.size(); ++i) {
            AbstractOrb orb = p.orbs.get(i);
            if (orb instanceof Dark) {
                orb.onStartOfTurn();
            }
        }
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(p, this));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Darkness();
    }
}

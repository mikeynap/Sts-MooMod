/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.relics;

import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import static com.micmoo.sts.moomod.MooModMod.BETA_RELIC;

/**
 *
 * @author mnapolit
 */
public class PowerPots extends MooRelic {

    public static final String ID = "moomod:PowerPots";
    public static final int AMOUNT = 1;
    public static final RelicType TYPE = RelicType.SHARED;

    public PowerPots() {
        super(ID, BETA_RELIC, AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return "Whenever you drink a potion, gain 1 strength.";
    }

    @Override
    public void onUsePotion() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, AMOUNT), AMOUNT));

    }

    @Override
    public AbstractRelic makeCopy() {
        return new PowerPots();
    }

}

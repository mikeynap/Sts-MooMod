/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.cards.blue;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import static com.micmoo.sts.moomod.MooModMod.BETA_POWER;

public class OrbDrain
extends CustomCard {
    public static final String ID = "moomod:OrbDrain";
    public static final String NAME = "OrbDrain";
    public static final String DESCRIPTION = "Remove all Orbs. NL Gain 1 Focus per Orb Removed.";
    public static final String UPGRADED_DESCRIPTION = "Evoke all Orbs. NL Gain 1 Focus per Orb Evoked.";
    public static final String IMG_PATH = BETA_POWER;
    private static final int COST = 1;
    private static final int UPGRADED_COST = 2;
    private static final int AMOUNT = 1;
    

    public OrbDrain() {
        
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = AMOUNT;
        
       // this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

      //  this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

       // this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int nOrbs = 0;
        while (p.hasOrb()){
            if (this.upgraded){
                p.evokeOrb();
            } else {
                p.removeNextOrb();
            }
            nOrbs++;
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FocusPower(p, nOrbs), nOrbs));
    }

    @Override
    public AbstractCard makeCopy() {
        return new OrbDrain();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.updateCost(1);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }
}

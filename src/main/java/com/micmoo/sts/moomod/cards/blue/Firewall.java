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
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Frost;
import static com.micmoo.sts.moomod.MooModMod.BETA_SKILL;

public class Firewall
extends CustomCard {
    public static final String ID = "moomod:Firewall";
    public static final String NAME = "Firewall";
    public static final String DESCRIPTION = "Lose !M! HP. Evoke all Orbs. Fill empty slots with Frost.";
    public static final String IMG_PATH = BETA_SKILL;
    private static final int COST = 1;
    private static final int HPCOST = 6;
    private static final int UPGRADED_HP_COST = -2;
    

    public Firewall() {
        
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

       this.baseMagicNumber = this.magicNumber = HPCOST;
       // this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

      //  this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

       // this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.baseMagicNumber));
        while (p.hasOrb()){
            p.evokeOrb();
        }
        
        for (int i = 0; i < p.orbs.size(); i++){
            p.channelOrb(new Frost());
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new Firewall();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.updateCost(-1);
            this.upgradeMagicNumber(UPGRADED_HP_COST);
        }
    }
}

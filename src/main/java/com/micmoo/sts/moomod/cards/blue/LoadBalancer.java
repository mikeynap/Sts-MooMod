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
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import static com.micmoo.sts.moomod.MooModMod.BETA_POWER;
import com.micmoo.sts.moomod.powers.LoadBalancerPower;

public class LoadBalancer
extends CustomCard {
    public static final String ID = "moomod:LoadBalancer";
    public static final String NAME = "LoadBalancer";
    public static final String DESCRIPTION = "At the beginning of your turn, channel a frost or lightning orb (whichever you have less of). If equal, channel a random orb.";
    public static final String IMG_PATH = BETA_POWER;
    private static final int COST = 2;
    

    public LoadBalancer() {
        
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 1;
        
       // this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

      //  this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

       // this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded){
                p.increaseMaxOrbSlots(1, true);
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoadBalancerPower(p, this.upgraded), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LoadBalancer();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Gain one orb slot. " + DESCRIPTION;
            this.initializeDescription();
        }
    }
}

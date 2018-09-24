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
import static com.micmoo.sts.moomod.MooModMod.BETA_POWER;
import com.micmoo.sts.moomod.powers.ZenPower;

public class ZenForm
extends CustomCard {
    public static final String ID = "moomod:ZenForm";
    public static final String NAME = "ZenForm";
    public static final String DESCRIPTION = "Gain !M! Focus each turn.";
    public static final String IMG_PATH = BETA_POWER;
    private static final int COST = 3;
    private static final int AMOUNT = 1;
    private static final int UPGRADED_AMOUNT = 1;
    

    public ZenForm() {
        
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = AMOUNT;
        
       // this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

      //  this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

       // this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ZenPower(p, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ZenForm();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
        }
    }
}

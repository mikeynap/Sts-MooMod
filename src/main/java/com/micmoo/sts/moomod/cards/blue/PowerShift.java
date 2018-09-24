/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.cards.blue;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import static com.micmoo.sts.moomod.MooModMod.BETA_POWER;

public class PowerShift
        extends CustomCard {

    public static final String ID = "moomod:PowerShift";
    public static final String NAME = "PowerShift";
    public static final String DESCRIPTION = "Gain !M! Focus. Lose 1 Strength. Lose 1 Dexterity.";
    public static final String IMG_PATH = BETA_POWER;
    private static final int COST = 1;
    private static final int AMOUNT = 2;
    private static final int UPGRADED_AMOUNT = 1;

    public PowerShift() {

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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FocusPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -1), -1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, -1), -1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PowerShift();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADED_AMOUNT);
        }
    }
}

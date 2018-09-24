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
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import static com.micmoo.sts.moomod.MooModMod.BETA_SKILL;
import java.util.ArrayList;

public class MultiRecursion
extends CustomCard {
    public static final String ID = "moomod:MultiRecursion";
    public static final String NAME = "MultiRecursion";
    public static final String DESCRIPTION = "Evoke all Orbs. NL Channel all Orbs Revoked.";
    public static final String IMG_PATH = BETA_SKILL;
    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    

    public MultiRecursion() {
        
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

        
       // this.setBackgroundTexture("img/custom_background_small.png", "img/custom_background_large.png");

      //  this.setOrbTexture("img/custom_orb_small.png", "img/custom_orb_large.png");

       // this.setBannerTexture("img/custom_banner_large.png", "img/custom_banner_large.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int nOrbs = 0;
        ArrayList<AbstractOrb> orbs = new ArrayList<>();
        
        for (int i = 0; i < p.orbs.size(); i++){
            orbs.add(p.orbs.get(i).makeCopy());
        }
        
        while (p.hasOrb()){
            p.evokeOrb();
        }
        
        orbs.forEach((orb) -> {
            p.channelOrb(orb);
        });
    }

    @Override
    public AbstractCard makeCopy() {
        return new MultiRecursion();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.updateCost(-1);
        }
    }
}

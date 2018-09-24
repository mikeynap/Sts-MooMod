/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.powers;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 *
 * @author Moo
 */
public class LoadBalancerPower extends AbstractPower {
    public static final String POWER_ID = "moomod:LoadBalancerPower";
    public static final String NAME = "LoadBalancerPower";
    public boolean isUpgraded = false;
    public static int total = 0;
    public LoadBalancerPower(final AbstractCreature owner, boolean upgraded) {
        this.name = LoadBalancerPower.NAME;
        this.ID = POWER_ID;
        this.isUpgraded = upgraded;
        this.owner = owner;
        this.amount++;
        this.description = "At the beginning of your turn, Channel an Orb.";
        this.loadRegion("focus");
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
    }
    
    public LoadBalancerPower(final AbstractCreature owner) {
        this(owner,false);
    }
    
    
    @Override
    public void atStartOfTurn() {
        AbstractPlayer p = (AbstractPlayer)this.owner;
        int nLightning = 0;
        int nFrost = 0;
        for (AbstractOrb orb : p.orbs){
            if (orb.getClass() == Lightning.class){
                nLightning++;
            } else if (orb.getClass() == Frost.class){
                nFrost++;
            }
        }
        for (int i = 0; i < amount; i++){
            AbstractOrb o;

            if (nLightning == nFrost) {
                o = AbstractOrb.getRandomOrb(true);
            } else if (nLightning > nFrost) {
                o = new Frost();
            } else {
                o = new Lightning();
            }
            if (o.getClass() == Lightning.class) {
                nLightning++;
            } else if (o.getClass() == Frost.class){
                nFrost++;
            }

            AbstractDungeon.actionManager.addToBottom(new ChannelAction(o));
        }
    }
    
    
    

}

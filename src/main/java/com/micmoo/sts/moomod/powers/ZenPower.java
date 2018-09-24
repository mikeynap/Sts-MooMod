/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;

/**
 *
 * @author Moo
 */
public class ZenPower extends AbstractPower {
    public static final String POWER_ID = "moomod:ZenPower";
    public static final String NAME = "ZenPower";
    public ZenPower(final AbstractCreature owner, final int amount) {
        this.name = ZenPower.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.description = "At the beginning of your turn, gain " +  this.amount + " focus";
        this.loadRegion("focus");
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
    }
    public ZenPower(final AbstractCreature owner) {
        this(owner,1);
    }
    
    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new FocusPower(this.owner, this.amount), this.amount));
    }
    
    
    

}

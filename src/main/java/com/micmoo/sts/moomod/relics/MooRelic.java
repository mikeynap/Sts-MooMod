/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.micmoo.sts.moomod.relics;

import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.micmoo.sts.moomod.MooModMod;

/**
 *
 * @author mnapolit
 */
public abstract class MooRelic extends AbstractRelic {

    public static final RelicType TYPE = RelicType.SHARED;

    public MooRelic(String setId, String imgName, RelicTier tier, LandingSound sfx) {
        super(setId, imgName, tier, sfx);

        if (imgName.startsWith("test")) {
            img = ImageMaster.loadImage("images/relics/" + imgName);
            largeImg = ImageMaster.loadImage("images/largeRelics/" + imgName);
            outlineImg = ImageMaster.loadImage("images/relics/outline/" + imgName);
        }
        if (img == null || outlineImg == null) {
            img = ImageMaster.loadImage(MooModMod.assetPath("images/relics/" + imgName));
            largeImg = ImageMaster.loadImage(MooModMod.assetPath("images/largeRelics/" + imgName));
            outlineImg = ImageMaster.loadImage(MooModMod.assetPath("images/relics/outline/" + imgName));
        }
    }

    RelicType getType() {
        return TYPE;
    }

}

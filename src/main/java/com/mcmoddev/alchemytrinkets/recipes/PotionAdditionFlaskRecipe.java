package com.mcmoddev.alchemytrinkets.recipes;

import java.util.regex.Pattern;

import com.mcmoddev.alchemytrinkets.Main;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class PotionAdditionFlaskRecipe extends ShapedOreRecipe{
	
	private NBTTagCompound currenttag = new NBTTagCompound();
	
	//public PotionAdditionFlaskRecipe(Block     result, Object... recipe){super(result, recipe); }
    //public PotionAdditionFlaskRecipe(Item      result, Object... recipe){ super(result, recipe); }
    public PotionAdditionFlaskRecipe(ItemStack result, ShapedPrimer recipe){
    	super(new ResourceLocation("mpab-shaped"), result, recipe); }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting craftMatrix) {

    	ItemStack newStack = output.copy();
    	

    	if(currenttag.getSize() > 0)
    		newStack.setTagCompound(currenttag);

    	return newStack;
    }
    
    @Override
    public boolean matches(InventoryCrafting inv, World world)
    {

        for (int x = 0; x <= inv.getWidth() - width; x++)
        {
            for (int y = 0; y <= inv.getHeight() - height; ++y)
            {
                if (checkMatch(inv, x, y, false))
                {
                    return customMatches(inv);
                }

                if (mirrored && checkMatch(inv, x, y, true))
                {
                    return customMatches(inv);
                }
            }
        }

        return false;
    }

	private boolean customMatches(InventoryCrafting inv){
		currenttag = new NBTTagCompound();
		ItemStack flask = inv.getStackInSlot(0);

		ItemStack potion = inv.getStackInSlot(1);
		if(flask != null && potion != null){
			if(flask.getTagCompound() != null){
				currenttag = flask.getTagCompound().copy();
				/*currenttag.setString("flaskComponent", flask.getTagCompound().getString("flaskComponent"));
				currenttag.setString("infusedGlass", flask.getTagCompound().getString("infusedGlass"));
				currenttag.setBoolean("isEmpty", flask.getTagCompound().getBoolean("isEmpty"));
				currenttag.setInteger("uses", flask.getTagCompound().getInteger("uses"));
				currenttag.setInteger("maxUses", flask.getTagCompound().getInteger("maxUses"));
				Main.logger.info("Flask has NBT: " + flask.getTagCompound());*/
			}
			if(potion.getTagCompound() != null){
				if(!currenttag.getBoolean("isEmpty")){
					if(currenttag.getInteger("uses") == currenttag.getInteger("maxUses")
							|| !flask.getTagCompound().getString("Potion").equals(potion.getTagCompound().getString("Potion"))){
						return false;
					}
					currenttag.setString("Potion", flask.getTagCompound().getString("Potion"));
					currenttag.setInteger("uses", currenttag.getInteger("uses") + 1);
				} else {
					currenttag.setString("Potion", potion.getTagCompound().getString("Potion"));
					currenttag.setBoolean("isEmpty", false);
					currenttag.setInteger("uses", 1);
				}
				Main.logger.info("Potion has NBT: " + potion.getTagCompound());
			}
		}
		return true;
	}
    private String getNameOfItem(String str){
    	return str.split(Pattern.quote("."), 3)[2].split("_", 2)[0];
    }
}

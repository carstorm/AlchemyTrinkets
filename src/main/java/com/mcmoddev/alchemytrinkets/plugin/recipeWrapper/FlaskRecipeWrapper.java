package com.mcmoddev.alchemytrinkets.plugin.recipeWrapper;

import com.google.common.collect.ImmutableList;
import com.mcmoddev.alchemytrinkets.plugin.JEIAlchemyTrinketsPlugin;
import com.mcmoddev.alchemytrinkets.recipes.FlaskRecipe;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collections;
import java.util.List;

public class FlaskRecipeWrapper implements ICustomCraftingRecipeWrapper {
    private FlaskRecipe theRecipe;

    public FlaskRecipeWrapper(FlaskRecipe recipe) {
        this.theRecipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        System.out.println("I am getting ingredients!");
        System.out.println(theRecipe.getInput());
        ingredients.setInputLists(ItemStack.class, theRecipe.getInput());
        ingredients.setOutputs(ItemStack.class, theRecipe.getOutput());
    }


    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, IIngredients iIngredients) {

    }
}

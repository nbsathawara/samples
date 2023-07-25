import { Component, OnInit } from '@angular/core';
import { Recipe } from './recipe.model';
import { RecipesService } from './recipes.service';
import { ViewDidEnter, ViewDidLeave, ViewWillEnter, ViewWillLeave } from '@ionic/angular';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.page.html',
  styleUrls: ['./recipes.page.scss'],
})
export class RecipesPage implements OnInit {

  recipes!: Recipe[]
  constructor(private recipeService: RecipesService) { }

  ngOnInit() {
  }

  ionViewWillEnter(): void {
    this.recipes = this.recipeService.getAllRecipes()
  }
}

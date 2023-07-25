import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipesService } from '../recipes.service';
import { Recipe } from '../recipe.model';
import { AlertController } from '@ionic/angular';

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.page.html',
  styleUrls: ['./recipe-details.page.scss'],
})
export class RecipeDetailsPage implements OnInit {


  recipe!: Recipe

  constructor(private activeRoute: ActivatedRoute,
    private router: Router,
    private alertCtrl: AlertController,
    private recipeService: RecipesService) { }

  ngOnInit() {
    this.activeRoute.paramMap.subscribe(
      paraMap => {
        if (!paraMap.has('recipeId')) {
          return
        }
        const recipeId = paraMap.get('recipeId') as string
        this.recipe = this.recipeService.getRecipe(recipeId) as Recipe
      }
    )
  }

  onDeleteRecipe() {
    this.alertCtrl.create({
      header: 'Confirmtion',
      message: 'Are you sure to delete recipe?',
      buttons: [
        {
          text: 'Delete',
          handler: () => {
            this.recipeService.deleteRecipe(this.recipe.id)
            this.router.navigate(['./recipes'])
          }
        },
        {
          text: 'Cancel',
          role: 'cancel'
        }
      ]
    }).then(alert=>{alert.present()})
  }

}

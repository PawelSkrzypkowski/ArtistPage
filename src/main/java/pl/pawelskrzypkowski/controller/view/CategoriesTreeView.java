package pl.pawelskrzypkowski.controller.view;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pawelskrzypkowski.entity.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Skrzypkowski
 * Wojskowa Akademia Techniczna im. Jarosława Dąbrowskiego, Warszawa 2018.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriesTreeView {

    private String text;
    private Long id;
    private List<CategoriesTreeView> nodes;

    public static CategoriesTreeView convertToTree(List<Category> categories, CategoriesTreeView categoriesTreeView){
        if(categoriesTreeView == null){
            categoriesTreeView = new CategoriesTreeView(null, null, new ArrayList<>());
        }
        for(Category category : categories){
            if((category.getParentCategory() != null && category.getParentCategory().getId().equals(categoriesTreeView.getId())) ||
                    (category.getParentCategory() == null && categoriesTreeView.getId() == null)){
                CategoriesTreeView newCategoriesTreeView = new CategoriesTreeView(category.getName(), category.getId(), new ArrayList<>());
                categoriesTreeView.getNodes().add(convertToTree(categories, newCategoriesTreeView));
            }
        }
        return categoriesTreeView;
    }
}

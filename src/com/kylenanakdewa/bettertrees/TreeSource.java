package com.kylenanakdewa.bettertrees;

import com.kylenanakdewa.bettertrees.treetemplates.TreeTemplate;
import com.kylenanakdewa.bettertrees.treetemplates.VanillaTreeTemplate;
import com.kylenanakdewa.bettertrees.treetemplates.CustomTreeTemplate;
import com.kylenanakdewa.bettertrees.treetemplates.BothTreesTemplate;
import org.bukkit.TreeType;

/**
 * The source of a tree's structure.
 */
enum TreeSource {

    /** Trees from vanilla Minecraft. */
    VANILLA(VanillaTreeTemplate.class),
    /** Custom trees in this plugin's folder. */
    CUSTOM(CustomTreeTemplate.class),
    /** Both vanilla and custom trees. */
    BOTH(BothTreesTemplate.class),
    /** No trees. */
    NONE(null);

    private Class<? extends TreeTemplate> treeTemplateClass;

    private TreeSource(Class<? extends TreeTemplate> treeTemplateClass){
        this.treeTemplateClass = treeTemplateClass;
    }

    /** Gets a new instance of TreeTemplate for the specified tree type. */
    TreeTemplate getTreeTemplate(TreeType treeType){
        try {
			return (TreeTemplate)treeTemplateClass.getConstructors()[0].newInstance(treeType);
		} catch (Exception e){
			// TODO Auto-generated catch block
            e.printStackTrace();
            return null;
		}
    }
}
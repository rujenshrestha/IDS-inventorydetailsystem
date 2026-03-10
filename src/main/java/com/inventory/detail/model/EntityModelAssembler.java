package com.inventory.detail.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.inventory.detail.controller.InventoryController;

@Component
public class EntityModelAssembler implements RepresentationModelAssembler<Inventory, EntityModel<Inventory>> {

	@Override
	public EntityModel<Inventory> toModel(Inventory inventory) {
		return null;
//		return EntityModel.of(inventory,
//				linkTo(methodOn(InventoryController.class).findById(inventory.getType(), inventory.getId()))
//						.withSelfRel(),
//				linkTo(methodOn(InventoryController.class).getAll(inventory.getType())).withRel(inventory.getType()));
	}

}

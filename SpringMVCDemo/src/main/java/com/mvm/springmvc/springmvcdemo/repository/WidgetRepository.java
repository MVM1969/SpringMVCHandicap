package com.mvm.springmvc.springmvcdemo.repository;

import org.springframework.data.repository.CrudRepository;

import com.mvm.springmvc.springmvcdemo.model.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Long> {
}
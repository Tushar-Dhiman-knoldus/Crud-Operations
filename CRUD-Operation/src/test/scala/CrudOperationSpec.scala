package com.knoldus.crudoperation

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.flatspec.AnyFlatSpec

class CrudOperationSpec extends AnyFlatSpec {

  "CrudOperationOnList" should "support create, read, update, and delete operations" in {
    val crud = new CrudOperationOnList[String]()
    assert(crud.create("apple") === List("apple"))
    assert(crud.create("banana") === List("apple", "banana"))
    assert(crud.read() === List("apple", "banana"))
    assert(crud.update("banana", "pear") === List("apple", "pear"))
    assert(crud.delete("apple") === List("pear"))
    assert(crud.delete("grape") === List("pear")) // grape is not in the list
    assert(crud.read() === List("pear"))
  }

  "CrudOperationOnSequence" should "support create, read, update, and delete operations" in {
    val crud = new CrudOperationOnSequence[Int]()
    assert(crud.create(1) === Seq(1))
    assert(crud.create(2) === Seq(1, 2))
    assert(crud.read() === Seq(1, 2))
    assert(crud.update(2, 3) === Seq(1, 3))
    assert(crud.delete(1) === Seq(3))
    assert(crud.delete(4) === Seq(3)) // 4 is not in the sequence
    assert(crud.read() === Seq(3))
  }

}

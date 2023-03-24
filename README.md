# Crud-Operations Assignment

### Problem Statement
Create an abstract class "CRUD operations" having 4 methods - create, read, update, delete. Create 2 classes and extend both of them with "CRUD operations" 
abstract class - one class would be using list to perform CRUD operations and the other class would be using seq to perform CRUD operations.

#### Source Code For the Problem
```
package com.knoldus.crudoperation
import scala.collection.mutable.ListBuffer
import scala.util.Try

abstract class CrudOperation[A] {
  def create(element: A): Iterable[A]
  def read(): Iterable[A]
  def update(elementToBeUpdated: A, element: A): Iterable[A]
  def delete(elementToBeDeleted: A): Iterable[A]
}

// class to implement CRUD Operation on List
class CrudOperationOnList[A]() extends CrudOperation[A] {
  private val genericList: ListBuffer[A] = ListBuffer.empty

  // Function to Create the list
  override def create(element: A): Iterable[A] = {
    Try {
      genericList += element
      genericList
    }.getOrElse(Seq.empty)
  }

  //Function to Read the list
  override def read(): Iterable[A] = {
    Try {
      genericList
    }.getOrElse(Seq.empty)
  }

  // Function to replace the element with the new element
  override def update(elementToBeUpdated: A, element: A): Iterable[A] = {
    val indexOfElementToBeUpdated = genericList.indexOf(elementToBeUpdated)
    Try {
      genericList.update(indexOfElementToBeUpdated, element)
      genericList
    }.getOrElse(Seq.empty)
  }

  // Function to delete the particular element
  override def delete(elementToBeDeleted: A): Iterable[A] = {
    val indexOfElementToBeDeleted = genericList.indexOf(elementToBeDeleted)
    Try {
      genericList.remove(indexOfElementToBeDeleted)
      genericList
    }.getOrElse(Seq.empty)
  }
}

class CrudOperationOnSequence[A]() extends CrudOperation[A] {
  private var genericSequence: Seq[A] = Seq.empty

  // Function to Create the list
  override def create(element: A): Iterable[A] = {
    Try {
      genericSequence = genericSequence :+ element
      genericSequence
    }.getOrElse(Seq.empty)
  }

  //Function to Read the list
  override def read(): Iterable[A] = {
    Try {
      genericSequence
    }.getOrElse(Seq.empty)
  }

  // Function to replace the element with the new element
  override def update(elementToBeUpdated: A, element: A): Iterable[A] = {
    val indexOfElementToBeUpdated = genericSequence.indexOf(elementToBeUpdated)
    Try {
      genericSequence = genericSequence.updated(indexOfElementToBeUpdated, element)
      genericSequence
    }.getOrElse(Seq.empty)
  }

  // Function to delete the particular element
  override def delete(elementToBeDeleted: A): Iterable[A] = {
    Try {
      genericSequence = genericSequence.filter(_ != elementToBeDeleted)
      genericSequence
    }.getOrElse(Seq.empty)
  }
}
```

### Test Cases for the Source Code
```
package com.knoldus.crudoperation
import org.scalatest.flatspec.AnyFlatSpec

class CrudOperationSpec extends AnyFlatSpec {

  "CrudOperationOnList" should "support create, read, update, and delete operations" in {
    val crud = new CrudOperationOnList[String]()
    assert(crud.create("apple") === List("apple"))
    assert(crud.create("banana") === List("apple", "banana"))
    assert(crud.read() === List("apple", "banana"))
    assert(crud.update("banana", "pear") === List("apple", "pear"))
    assert(crud.delete("apple") === List("pear"))
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
```


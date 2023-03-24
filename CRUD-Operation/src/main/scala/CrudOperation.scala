package com.knoldus.crudoperation

import scala.collection.mutable.ListBuffer

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
    genericList += element
    genericList
  }

  //Function to Read the list
  override def read(): Iterable[A] = {
    genericList
  }

  // Function to replace the element with the new element
  override def update(elementToBeUpdated: A, element: A): Iterable[A] = {
    val indexOfElementToBeUpdated = genericList.indexOf(elementToBeUpdated)
    genericList.update(indexOfElementToBeUpdated, element)
    genericList
  }

  // Function to delete the particular element
  override def delete(elementToBeDeleted: A): Iterable[A] = {
    val indexOfElementToBeDeleted = genericList.indexOf(elementToBeDeleted)
    genericList.remove(indexOfElementToBeDeleted)
    genericList
  }
}

class CrudOperationOnSequence[A]() extends CrudOperation[A] {

  private var genericSequence: Seq[A] = Seq.empty

  // Function to Create the list
  override def create(element: A): Iterable[A] = {
    genericSequence = genericSequence :+ element
    genericSequence
  }

  //Function to Read the list
  override def read(): Iterable[A] = {
    genericSequence
  }

  // Function to replace the element with the new element
  override def update(elementToBeUpdated: A, element: A): Iterable[A] = {
    val indexOfElementToBeUpdated = genericSequence.indexOf(elementToBeUpdated)
    genericSequence = genericSequence.updated(indexOfElementToBeUpdated, element)
    genericSequence
  }

  // Function to delete the particular element
  override def delete(elementToBeDeleted: A): Iterable[A] = {
    genericSequence = genericSequence.filter(_ != elementToBeDeleted)
    genericSequence
  }
}





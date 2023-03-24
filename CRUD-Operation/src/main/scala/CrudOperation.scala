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



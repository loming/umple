<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3036 modeling language!*/

class QueuedWithNestedStateMachines
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //QueuedWithNestedStateMachines State Machines
  private static $SmS1 = 1;
  private static $SmS2 = 2;
  private static $SmS2Null = 3;
  private static $SmS2S2a = 4;
  private static $SmS2S2b = 5;
  private $sm;
  private $smS2;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->setSmS2(self::$SmS2Null);
    $this->setSm(self::$SmS1);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getSmFullName()
  {
    $answer = $this->getSm();
    if ($this->smS2 != self::$SmS2Null) { $answer .= "." . $this->getSmS2(); }
    return $answer;
  }

  public function getSm()
  {
    if ($this->sm == self::$SmS1) { return "SmS1"; }
    elseif ($this->sm == self::$SmS2) { return "SmS2"; }
    return null;
  }

  public function getSmS2()
  {
    if ($this->smS2 == self::$SmS2Null) { return "SmS2Null"; }
    elseif ($this->smS2 == self::$SmS2S2a) { return "SmS2S2a"; }
    elseif ($this->smS2 == self::$SmS2S2b) { return "SmS2S2b"; }
    return null;
  }

  public function e1()
  {
    $wasEventProcessed = false;
    
    $aSm = $this->sm;
    if ($aSm == self::$SmS1)
    {
      $this->setSm(self::$SmS2);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function enterS2()
  {
    $wasEventProcessed = false;
    
    $aSmS2 = $this->smS2;
    if ($aSmS2 == self::$SmS2Null)
    {
      $this->setSmS2(self::$SmS2S2a);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitS2()
  {
    $wasEventProcessed = false;
    
    $aSmS2 = $this->smS2;
    if ($aSmS2 == self::$SmS2S2a)
    {
      $this->setSmS2(self::$SmS2Null);
      $wasEventProcessed = true;
    }
    elseif ($aSmS2 == self::$SmS2S2b)
    {
      $this->setSmS2(self::$SmS2Null);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function e2()
  {
    $wasEventProcessed = false;
    
    $aSmS2 = $this->smS2;
    if ($aSmS2 == self::$SmS2S2a)
    {
      $this->setSmS2(self::$SmS2S2b);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  public function e3()
  {
    $wasEventProcessed = false;
    
    $aSmS2 = $this->smS2;
    if ($aSmS2 == self::$SmS2S2b)
    {
      $this->setSmS2(self::$SmS2S2a);
      $wasEventProcessed = true;
    }
    return $wasEventProcessed;
  }

  private function exitSm()
  {
    if ($this->sm == self::$SmS2)
    {
      $this->exitS2();
    }
  }

  private function setSm($aSm)
  {
    $this->sm = $aSm;

    // entry actions and do activities
    if ($this->sm == self::$SmS2)
    {
      if ($this->smS2 == self::$SmS2Null) { $this->setSmS2(self::$SmS2S2a); }
    }
  }

  private function setSmS2($aSmS2)
  {
    $this->smS2 = $aSmS2;
    if ($this->sm != self::$SmS2 && $aSmS2 != self::$SmS2Null) { $this->setSm(self::$SmS2); }
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>
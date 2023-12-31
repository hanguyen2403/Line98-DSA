# Line98-DSA

<p align="center">
  <img src="Resources\Resources\start\scene.png" alt="Logo">
</p>
<h1 align="center"> Line98 </h1>
<h3 align="center"> HCMIU - DSA Project - Semester 1 2023 - 2024 </h3>



<!-- TABLE OF CONTENTS -->
<h2 id="table-of-contents"> Table of Contents</h2>

<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#Introduction">➤ Introduction</a>
      <ol>
        <li><a href="#Team Members"> Team Members</a></li>
        <li><a href="#Rule"> Rule</a></li>
        <li><a href="#How to run"> How to run</a></li>
      </ol>
    </li>
    <li><a href="#about-the-project"> ➤ About The Project</a>
      <ol>
        <li><a href="#built-with"> Built With</a></li>
        <li><a href="#uml"> UML</a></li>
        <li><a href="#probs"> Original source code problems</a></li>
        <li><a href="#BFS"> DSA apply</a></li>        
        <li><a href="#new-features"> Upgraded Features</a></li>
      </ol>
    </li>
    <li><a href="#references"> ➤ References</a></li>
  </ol>
</details>

<!-- INTRODUCTION -->
<h2 id="Introduction"> Introduction</h2>

<q>
This is our Line 98 project for the Data Structure and Algorithm course at HCMIU in semester 1 (2023-2024).
Line 98 is a classic puzzle game that involves moving colored balls to form lines, squares, or blocks of the same color. The game is based on the original Lines 98 game developed by Igor Nedelko and Andrey Akselrod in 1998. The game is implemented in Java using the Swing library for graphics and user interface. We hope you enjoy it!
</q>
<h3 id="Team Members"> Team Members </h3>
<table>
    <tr>
        <th>No.</th>
        <th>Name</th>
        <th>StudentID</th>
    </tr>
    <tr>
        <td>1</td>
        <td>Nguyễn Khánh Hà</td>
        <td>ITCSIU21004</td>
    </tr>
    <tr>
        <td>2</td>
        <td>Phạm Anh Huy</td>
        <td>ITCSIU21133</td>
    </tr>
    <tr>
        <td>3</td>
        <td>Trần Quang Bảo Duy</td>
        <td>ITCSIU21176</td>
    </tr>
</table>

<h3 id="Rule"> Rule </h3>
<q>
The game consists of a 10x10 board with randomly placed balls of different colors. The player can select a ball and move it to an empty cell, as long as there is a clear path between the original and the destination cells. The goal is to form horizontal, vertical, or diagonal lines of at least five balls of the same color, which will then disappear and score points. The game also supports two other modes: square mode, where the player has to form squares of four balls of the same color, and block mode, where the player has to form blocks of seven adjacent balls of the same color. The game ends when the board is full and no more moves are possible.
</q>

<img src="Resources/Resources/Game Play/gamescene.png" alt="Logo">

<h3 id="How to run"> How to run </h3>
<ol>
<li> Clone this repository</li>

    git clone https://github.com/hanguyen2403/Line98-DSA.git

<li> Open the project with IntelliJ IDEA or VSCode and check the file status</li>

    git status

<li> Run the project</li>
<li> Enjoy the game</li>
</ol>

<!-- ABOUT THE PROJECT -->

<h2 id="about-the-project"> About The Project</h2>

<h3 id="built-with"> Built With </h3>
  <ul>
    <li>IDE: IntelliJ IDEA</li>
    <li>Language: Java</li>
    <li>Library: standard Java libraries, JavaFX (for sound feartures)</li>
    <li>Tools: Paint / Adobe Photoshop CS6 / Canvas (design particular objects)</li>
  </ul>
<h3 id="uml"> UML </h3>
<img src="Resources\Resources\UML Diagram\picture 1.PNG" alt="uml1">
<img src="Resources\Resources\UML Diagram\picture 2.PNG" alt="uml2" >
<img src="Resources\Resources\UML Diagram\picture 3.PNG" alt="uml2">
<h3 id="probs"> Original source code problems </h3>
  <ul>
    <li>Don't have start and end scene</li>
    <li>Single round</li>
    <li>Non user-friendly interface</li>
  </ul>
<h3 id="BFS"> DSA apply </h3>
    <h4 id="BFS"> Breadth First Search (BFS)</h4>
        <p> The concept of this is first at the start point it will find around them if there is a blank cell that the ball can go, after finding if it have any path, the program will assign the root point to the branch path and then set the brand point as a root point following that it will continuously find until finds the destination path, so that the program will return true and the path is displayed on the scene. If there is not the program will return false and wait for the player to click on another cell. </p> 
        <img src="Resources/Resources/Game Play/BFS.png" >
    <h4 id="Linked_list"> Linked List </h4>
        <p> The undo button is built based on the idea of Doubly Linked List. Each Link will store the data of the board(which is the ball, color and score) and the Linked List will have a method to insert and delete each Link. When the game is processed, each move will call the “saveUndo()” method and save the state of the board, and when the undo button is pressed, the “Undo()” method will get the state inside the Linked List and the board will come back to that state. Additionally, to balance the game, the undo button is limited to 5 times </p>
        <img src="Resources/Resources/Game Play/Linkedlist.png" >
<h3 id="new-features"> Upgraded Features </h3>
  <ul>
    <li>Add undo feature </li>
    <li>Add design pattern (Command)</li>
  </ul>


<!-- REFERENCES -->
<h2 id="references"> References </h2>
<a href="https://katatunix.wordpress.com/2010/11/23/thu%E1%BA%ADt-toan-c%E1%BB%A7a-game-lines98-p1/"> Lines98 </a> 
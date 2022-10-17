


let h1 = document.createElement('h1');
h1.textContent = "Empowering women";
h1.style.textAlign = "center";
h1.style.fontSize = "2.5em";
h1.style.backgroundColor = "#DEF2F1";
h1.style.color = "#9e5d5d";
h1.style.padding = "60px";
h1.style.marginLeft = "40px";
h1.style.marginRight = "40px";
h1.style.fontSize = "3.5rem";

document.body.appendChild(h1);
document.body.style.backgroundColor = "#2b7a78";



let parentDiv = document.createElement('div');
parentDiv.style.padding = "30px";
document.body.appendChild(parentDiv);

let Olist1 = document.createElement('ol');
Olist1.style.fontSize = "20px";
Olist1.style.backgroundColor = "#DEF2F1";
Olist1.style.color = "#9e5d5d";
Olist1.style.textAlign = "left";
parentDiv.appendChild(Olist1);

let li1 = document.createElement('li'); 
li1.textContent = "Women in STEM";
Olist1.appendChild(li1);

let li2 = document.createElement('li'); 
li2.textContent = "Women in Entertainment industry";
Olist1.appendChild(li2);

let li3 = document.createElement('li'); 
li3.textContent = "Women in leadership Position";
Olist1.appendChild(li3);

let li4 = document.createElement('li'); 
li4.textContent = "Women of color";
Olist1.appendChild(li4);

let li5 = document.createElement('li'); 
li5.textContent = "LGBTQ community";
Olist1.appendChild(li5);

//  create a div and add it to the parent div

let firstChildDiv =  document.createElement('div');
firstChildDiv.style.display = "flex";
parentDiv.appendChild(firstChildDiv);

let ChildDiv1_1 = document.createElement('div');
ChildDiv1_1.style.display = "block";
firstChildDiv.appendChild(ChildDiv1_1);

let image1 = document.createElement('img');
image1.setAttribute("src", "https://hips.hearstapps.com/hmg-prod/images/square-1572461505.png?resize=980:* ");
// image1.setAttribute("width", "50%");
image1.style.padding = "5px";
image1.style.flex = "50%";
ChildDiv1_1.appendChild(image1);

let ChildDiv1_2 = document.createElement('div');
ChildDiv1_2.style.display = "block";

firstChildDiv.appendChild(ChildDiv1_2);
let image2 = document.createElement('img');
image2.setAttribute("src", "https://i.pinimg.com/originals/6d/db/a6/6ddba64b0d03e6bb06d9e16a95ab12a5.gif");
// image2.setAttribute("width", "50%");
image2.style.padding = "5px";
image2.style.flex = "50%";
ChildDiv1_2.appendChild(image2);



let blockquote1 = document.createElement('blockquote');
blockquote1.setAttribute( "cite", "https://www.harpersbazaar.com/culture/features/a4056/empowering-female-quotes/");
blockquote1.textContent = " I can promise you that women working together linked, informed and educated can bring peace and prosperity to this forsaken planet";
blockquote1.style.fontSize = "2em";
blockquote1.style.textAlign = "center";
blockquote1.style.lineHeight= "1.5";
blockquote1.style.margin= "1.5em";
blockquote1.style.padding= "0.5em";
blockquote1.style.borderLeft = "10";
blockquote1.style.display = "block";
blockquote1.style.color = "#b999be";
blockquote1.style.borderLeft = "10px";
blockquote1.style.borderLeftStyle = "Solid";
blockquote1.style.borderLeftColor = "rgb(204, 204, 204";






parentDiv.appendChild(blockquote1);

let em1 = document.createElement('em');
em1.textContent= " Isabelle Allende";
blockquote1.appendChild(em1);

let blockquote2 = document.createElement('blockquote');
blockquote2.setAttribute ("cite", "https://www.harpersbazaar.com/culture/features/a4056/empowering-female-quotes/" );
blockquote2.textContent = " The more I have spoken about feminism the more I have realized that fighting for women's rights has too often become synonymous with man-hating. If there is one thing I know for certain, it is that this has to stop";
blockquote2.style.fontSize = "2em";
blockquote2.style.textAlign = "center";
blockquote2.style.lineHeight= "1.5";
blockquote2.style.lineHeight= "1.5";
blockquote2.style.margin= "1.5em";
blockquote2.style.padding= "0.5em";
blockquote2.style.borderLeft = "10";
blockquote2.style.display = "block";
blockquote2.style.color = "#b999be";
blockquote2.style.borderLeft = "10px";
blockquote2.style.borderLeftStyle = "Solid";
blockquote2.style.borderLeftColor = "rgb(204, 204, 204";
parentDiv.appendChild(blockquote2);

let em2 = document.createElement('em');
em2.textContent= " Emma Watson";
blockquote2.appendChild(em2);


////

let blockquote3 = document.createElement('blockquote');
blockquote3.setAttribute ("cite", "https://www.harpersbazaar.com/culture/features/a4056/empowering-female-quotes/" );
blockquote3.textContent = "There's something so special about a woman who dominates in a man's world. It takes a certain grace, strength, intelligence, fearlessness, and the nerve to never take no for an answer ";
blockquote3.style.fontSize = "2em";
blockquote3.style.textAlign = "center";
blockquote3.style.lineHeight= "1.5";
blockquote3.style.lineHeight= "1.5";
blockquote3.style.margin= "1.5em";
blockquote3.style.padding= "0.5em";
blockquote3.style.borderLeft = "10";
blockquote3.style.display = "block";
blockquote3.style.color = "#b999be";
blockquote3.style.borderLeft = "10px";
blockquote3.style.borderLeftStyle = "Solid";
blockquote3.style.borderLeftColor = "rgb(204, 204, 204";
parentDiv.appendChild(blockquote3);

let em3 = document.createElement('em');
em3.textContent= "Rihanna";
blockquote3.appendChild(em3);




// 



let divForLink = document.createElement('div');
parentDiv.appendChild(divForLink);

let link1 = document.createElement('a');
link1.setAttribute("href", "https://www.harpersbazaar.com/culture/features/a4056/empowering-female-quotes/");
link1.textContent = "Click me for more quotes";
link1.style.textDecoration = "none";
link1.style.fontSize = "1em";
link1.style.float = "right";
link1.style.padding = "14px 25px";
link1.style.color = "white";
link1.style.backgroundColor = "#ffb6c1";
divForLink.appendChild(link1);

let lastDiv = document.createElement('div');
parentDiv.appendChild(lastDiv);


let paragraph = document.createElement('p');
paragraph.textContent = " List of my favorite women of all times"
paragraph.style.fontSize = "20px";
paragraph.style.color = "rgb(158, 93, 93)";
paragraph.style.background = "rgb(222, 242, 241)";
paragraph.style.textAlign = "left";
paragraph.style.padding = "10px 20px";
paragraph.style.display = "inline-block";
lastDiv.appendChild(paragraph);


let UnorderedList = document.createElement('ul');
UnorderedList.style.fontSize = "20px";
UnorderedList.style.backgroundColor = "#DEF2F1";
UnorderedList.style.color = "#9e5d5d";
UnorderedList.style.textAlign = "left";
UnorderedList.style.display = "inline-block";
UnorderedList.style.padding = "14px 25px";


parentDiv.appendChild(UnorderedList);

let uList1 = document.createElement('li');
uList1.textContent = "My mother"
UnorderedList.appendChild(uList1);

let uList2 = document.createElement('li');
uList2.textContent = "Emma Watson"
UnorderedList.appendChild(uList2);

let uList3 = document.createElement('li');
uList3.textContent = "Rihanna"
UnorderedList.appendChild(uList3);

let uList4 = document.createElement('li');
uList4.textContent = "Michelle Obama"
UnorderedList.appendChild(uList4);


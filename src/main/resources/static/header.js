

document.addEventListener("DOMContentLoaded" , ()=>{
const navBtn = document.getElementById("nav-btn");
console.log(navBtn , "nav bth");

const sidebar = document.getElementById("sidebar");

console.log(sidebar , "sidebar");

const sidebarClose = document.getElementById("sidebar-close");

  console.log(sidebarClose , "as;lmas;m");
  

const overlay = document.getElementById("overlay");


console.log(overlay , "alksjdsk");


function openSidebar() {
  sidebar.classList.add("active");
  overlay.style.display = "block";
  document.body.style.overflow = "hidden";
}

function closeSidebar() {
  sidebar.classList.remove("active");
  overlay.style.display = "none";
  document.body.style.overflow = "auto";
}

navBtn.addEventListener("click", openSidebar);
sidebarClose.addEventListener("click", closeSidebar);
})

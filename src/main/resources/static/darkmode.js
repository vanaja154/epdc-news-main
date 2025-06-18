// Function to handle news container auto-scroll
function setupNewsContainer() {
  const newsContainer = document.getElementById("newsContainer");

  if (newsContainer) {
    // Clone the first 6 cards and append them to the end of the container
    const cards = newsContainer.querySelectorAll(".news-card");
    const firstSixCards = Array.from(cards).slice(0, 6);

    firstSixCards.forEach((card) => {
      const clone = card.cloneNode(true);
      newsContainer.appendChild(clone);
    });

    // Function to automatically scroll from left to right
    function autoScroll() {
      const maxScrollLeft = newsContainer.scrollWidth - newsContainer.clientWidth;

      if (newsContainer.scrollLeft >= maxScrollLeft / 2) {
        newsContainer.scrollTo({ left: 0, behavior: "instant" });
      } else {
        newsContainer.scrollBy({ left: 2, behavior: "smooth" });
      }
    }

    // Start the auto-scroll every 30ms
    let scrollInterval = setInterval(autoScroll, 30);

    // Pause scrolling on hover
    newsContainer.addEventListener("mouseenter", () =>
      clearInterval(scrollInterval)
    );
    newsContainer.addEventListener("mouseleave", () => {
      scrollInterval = setInterval(autoScroll, 30);
    });
  } else {
    console.error("News container not found!");
  }
}

// Function to handle hamburger menu toggle
function setupHamburgerMenu() {
  const hamburgerIcon = document.querySelector("#hamburger");
  const closeIcon = document.querySelector("#close-icon");
  const navMenu = document.querySelector("nav ul");

  if (hamburgerIcon && closeIcon && navMenu) {
    hamburgerIcon.addEventListener("click", function () {
      navMenu.classList.add("show");
      hamburgerIcon.style.display = "none";
      closeIcon.style.display = "block";
    });

    closeIcon.addEventListener("click", function () {
      navMenu.classList.remove("show");
      closeIcon.style.display = "none";
      hamburgerIcon.style.display = "block";
    });
  } else {
    console.error("Hamburger menu elements not found!");
  }
}

// Initialize all functionality
document.addEventListener("DOMContentLoaded", function () {
  console.log("Script loaded successfully!");

  setupNewsContainer();
  setupHamburgerMenu();
});



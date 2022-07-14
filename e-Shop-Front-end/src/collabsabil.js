const collapsibles = document.querySelectorAll(".CategoriesList__item");
collapsibles.forEach((item) =>
  item.addEventListener("click", function () {
    this.classList.toggle("CategoriesList__item--expanded");
  })
);

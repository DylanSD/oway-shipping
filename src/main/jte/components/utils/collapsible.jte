@import gg.jte.Content

@param String id
@param boolean isOpenAtInit = false
@param Content titleContent
@param Content openContent

<style>
    .collapsible-header {
        display: flex;
        align-items: center;
        cursor: pointer;
        user-select: none;
    }

    .arrow {
        transition: transform 0.2s ease;
        margin-right: 8px;
        display: inline-block;
    }

    .arrow.open {
        transform: rotate(90deg);
    }
</style>

<div>
    <div class="collapsible-header" onclick="toggleDivVisibility('${id}')">
        <span id="toggle-arrow-${id}"
              class='arrow${isOpenAtInit ? " open" : ""}'>
            &#9654;
        </span>

        ${titleContent}
    </div>

    <div id="toggle-content-${id}"
         style='display:${isOpenAtInit ? "block" : "none"};'>
        ${openContent}
    </div>
</div>

<script>
    function toggleDivVisibility(id) {
        var content = document.getElementById('toggle-content-' + id);
        var arrow = document.getElementById('toggle-arrow-' + id);

        if (content.style.display === "none") {
            content.style.display = "block";
            arrow.classList.add("open");
        } else {
            content.style.display = "none";
            arrow.classList.remove("open");
        }
    }
</script>

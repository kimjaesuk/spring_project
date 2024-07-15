document.addEventListener('DOMContentLoaded', () => {
    initializePostLike();
    initializeCommentLikes();
});

function initializePostLike() {
    const likeButton = document.getElementById('likeButton');
    if (likeButton) {
        const postId = likeButton.dataset.postId;
        const userId = 'currentUserId'; // 실제 사용자 ID로 대체 필요

        initializeLikeStatus(postId, userId);

        likeButton.addEventListener('click', () => {
            togglePostLike(postId, userId);
        });
    }
}

function initializeCommentLikes() {
    const likeButtons = document.querySelectorAll('.like-button');
    likeButtons.forEach(button => {
        button.addEventListener('click', function() {
            const commentId = this.getAttribute('data-comment-id');
            const userId = 'currentUserId'; // 실제 사용자 ID로 대체 필요
            toggleCommentLike(commentId, userId);
        });
    });
}

function togglePostLike(postId, userId) {
    fetch(`/jaesuk/post/${postId}/like?userId=${userId}`, { method: 'POST' })
        .then(response => response.json())
        .then(data => {
            updatePostLikeUI(data.liked, data.likeCount);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('좋아요 처리 중 오류가 발생했습니다.');
        });
}

function toggleCommentLike(commentId, userId) {
    fetch(`/jaesuk/comment/${commentId}/like?userId=${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.json())
        .then(data => {
            updateCommentLikeUI(commentId, data.liked, data.likeCount);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('좋아요 처리 중 오류가 발생했습니다.');
        });
}

function updatePostLikeUI(isLiked, likeCount) {
    const likeButton = document.getElementById('likeButton');
    const likeCountElement = document.getElementById('likeCount');
    const likeIcon = document.querySelector('#likeButton svg path');

    if (isLiked) {
        likeButton.classList.add('liked');
        likeIcon.setAttribute('fill', '#ff0000');
    } else {
        likeButton.classList.remove('liked');
        likeIcon.setAttribute('fill', '#000000');
    }

    likeCountElement.textContent = likeCount;
    likeCountElement.classList.add('updated');
    setTimeout(() => {
        likeCountElement.classList.remove('updated');
    }, 300);
}

function updateCommentLikeUI(commentId, isLiked, likeCount) {
    const likeButton = document.querySelector(`.like-button[data-comment-id="${commentId}"]`);
    const likeCountElement = likeButton.querySelector('.like-count');
    const likeIcon = likeButton.querySelector('svg');

    if (isLiked) {
        likeButton.classList.add('liked');
        likeIcon.innerHTML = '<path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>';
    } else {
        likeButton.classList.remove('liked');
        likeIcon.innerHTML = '<path d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>';
    }

    likeCountElement.textContent = likeCount;
    likeButton.classList.add('updated');
    setTimeout(() => {
        likeButton.classList.remove('updated');
    }, 300);
}

function initializeLikeStatus(postId, userId) {
    fetch(`/jaesuk/post/${postId}/like-status?userId=${userId}`)
        .then(response => response.json())
        .then(data => {
            updatePostLikeUI(data.liked, data.likeCount);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
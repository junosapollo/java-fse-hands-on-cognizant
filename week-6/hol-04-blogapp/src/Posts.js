import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      error: null
    };
  }

  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Unable to load posts');
        }
        return response.json();
      })
      .then((posts) => {
        const postList = posts.map(
          (post) => new Post(post.userId, post.id, post.title, post.body)
        );
        this.setState({ posts: postList });
      })
      .catch((error) => {
        this.setState({ error });
        alert(error.message);
      });
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error) {
    this.setState({ error });
    alert(error.message);
  }

  render() {
    if (this.state.error) {
      return <p>Something went wrong while loading posts.</p>;
    }

    return (
      <section>
        <h1>Posts</h1>
        {this.state.posts.map((post) => (
          <article className="post" key={post.id}>
            <h2>{post.title}</h2>
            <p>{post.body}</p>
          </article>
        ))}
      </section>
    );
  }
}

export default Posts;

import { useState } from 'react'

function App() {
  const [language, setLanguage] = useState('java')
  const [issues, setIssues] = useState([])
  const [loading, setLoading] = useState(false)
  const [claimedIssues, setClaimedIssues] = useState([])

  const findIssues = async () => {
    setLoading(true)
    try {
      const response = await fetch(`http://localhost:8080/api/issues?language=${language}`)
      const data = await response.json()
      setIssues(data)
    } catch (error) {
      console.error('Failed to fetch issues:', error)
    }
    setLoading(false)
  }

  const claimIssue = async (issue) => {
    try {
      await fetch('http://localhost:8080/api/claim', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          title: issue.title,
          html_url: issue.html_url,
          repository_url: issue.repository_url
        })
      })
      alert('Issue claimed!')
    } catch (error) {
      console.error('Failed to claim issue:', error)
    }
  }

    const loadClaimedIssues = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/claimed')
      const data = await response.json()
      setClaimedIssues(data)
    } catch (error) {
      console.error('Failed to load claimed issues:', error)
    }
  }

  return (
    <div>
      <h1>Merged</h1>
      <p>Find good-first-issues matched to your stack.</p>

      <select value={language} onChange={(e) => setLanguage(e.target.value)}>
        <option value="java">Java</option>
        <option value="javascript">JavaScript</option>
        <option value="python">Python</option>
      </select>

      <button onClick={findIssues}>Find Issues</button>

      {loading && <p>Loading...</p>}

            <ul>
        {issues.map((issue) => (
          <li key={issue.html_url}>
            <a href={issue.html_url} target="_blank" rel="noreferrer">
              {issue.title}
            </a>
            <button onClick={() => claimIssue(issue)}>Claim</button>
          </li>
        ))}
      </ul>

      <hr />
      <h2>My Claimed Issues</h2>
      <button onClick={loadClaimedIssues}>Refresh Claimed Issues</button>

      <ul>
        {claimedIssues.map((claimed) => (
          <li key={claimed.id}>
            {claimed.issueTitle} — {claimed.prLink ? 'PR submitted' : 'No PR yet'}
          </li>
        ))}
      </ul>
    </div>
  )
}

export default App